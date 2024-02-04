package com.example.dao

import com.example.data.*
import com.example.models.User
import com.example.security.Hashing
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

    fun dbForRepos() : Database{
        return database
    }
}