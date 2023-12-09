package com.example.dao

import com.example.models.*
import com.example.security.Hashing
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
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
            // Зачем дропаем таблицы при каждом запуске приложения? Мы же потеряем все данные которые там лежат(
            //SchemaUtils.drop(MessageStatuses)
            //SchemaUtils.drop(Messages)
            //SchemaUtils.drop(Chats)
            SchemaUtils.drop(UserTable)
            SchemaUtils.createMissingTablesAndColumns(UserTable)
            //SchemaUtils.createMissingTablesAndColumns(Chats)
            //SchemaUtils.createMissingTablesAndColumns(Messages)
            //SchemaUtils.createMissingTablesAndColumns(MessageStatuses)
        }
    }

    /*suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) {block()}*/
}