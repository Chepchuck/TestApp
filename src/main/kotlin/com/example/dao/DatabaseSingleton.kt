package com.example.dao

import com.example.models.Chats
import com.example.models.MessageStatuses
import com.example.models.Messages
import com.example.models.Users
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseSingleton {
    fun init(){
        val driverClassName = "org.postgresql.Driver"
        val jdbcURL = "jdbc:postgresql://localhost:5432/news"
        val database = Database.connect(jdbcURL, driverClassName)

        transaction(database) {
            SchemaUtils.drop(MessageStatuses)
            SchemaUtils.drop(Messages)
            SchemaUtils.drop(Chats)
            SchemaUtils.drop(Users)
            SchemaUtils.createMissingTablesAndColumns(Users)
            SchemaUtils.createMissingTablesAndColumns(Chats)
            SchemaUtils.createMissingTablesAndColumns(Messages)
            SchemaUtils.createMissingTablesAndColumns(MessageStatuses)
        }

    }

    /*suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) {block()}*/
}