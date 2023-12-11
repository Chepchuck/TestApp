package com.example.dao

import com.example.data.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseSingleton {
    fun init(){
        val driverClassName = "org.postgresql.Driver"
        val jdbcURL = "jdbc:postgresql://localhost:5432/news"
        val userDB = "admin"
        val password = "1234"
        val database = Database.connect(jdbcURL, driver = driverClassName,
            user = userDB, password = password)

        transaction(database) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.createMissingTablesAndColumns(UserTable)
            SchemaUtils.createMissingTablesAndColumns(ChatTable)
            SchemaUtils.createMissingTablesAndColumns(MessageTable)
            SchemaUtils.createMissingTablesAndColumns(MessageStatusTable)
            SchemaUtils.createMissingTablesAndColumns(ChatParticipantsTable)
        }
    }
}