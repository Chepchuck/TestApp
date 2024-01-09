package com.example.dao

import com.example.data.*
import com.example.models.User
import com.example.security.digestFunction
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseSingleton {
    private const val DRIVER_CLASS_NAME = "org.postgresql.Driver"
    private const val JDBC_URL = "jdbc:postgresql://localhost:5432/news"
    private const val USER_DB = "admin"
    private const val PASSWORD = "1234"
    private val database = Database.connect(JDBC_URL, driver = DRIVER_CLASS_NAME,
        user = USER_DB, password = PASSWORD)
    fun init(){
        transaction(database) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.createMissingTablesAndColumns(UserTable)
            SchemaUtils.createMissingTablesAndColumns(ChatTable)
            SchemaUtils.createMissingTablesAndColumns(MessageTable)
            SchemaUtils.createMissingTablesAndColumns(MessageStatusTable)
            SchemaUtils.createMissingTablesAndColumns(ChatParticipantsTable)
        }
    }

    fun createUser(/*user: User*/){
        transaction(database){
            if (UserTable.selectAll().empty()) {
                UserTable.insert {
                    it[login] = "admin" //user.login
                    it[firstName] = "Alexander" //user.password
                    it[lastName] = "Chistyakov" //user.lastName
                    it[passHash] = digestFunction("1234" /*user.password*/).toString()
                }
            }
        }
    }

    fun selectAuthInfo(): Map<String, ByteArray>{
        val userList = transaction(database) {
            UserTable
                .slice(UserTable.login, UserTable.passHash)
                .selectAll()
                .map {
                    mapOf(it[UserTable.login] to it[UserTable.passHash].toByteArray())
                }
        }
        val usersMap: MutableMap<String, ByteArray> = mutableMapOf()
        for(i in userList){
            for ((key, value) in i){
                usersMap += (key to value)
            }
        }
        return usersMap
    }
}