package com.example

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test

class PGTest {

    @Test fun test() {
        DbSettings.db

        transaction {
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(User)
            val First = User.insert{
                it[name] = "Alex"
            } get User.id

            println("Users: ${User.selectAll()}")
        }
    }

    object User: IntIdTable(){
        val name = varchar("name", 50)
    }

    object DbSettings{
        val db by lazy{
            Database.connect("jdbc:postgresql://localhost:5432/news", driver = "org.postgresql.Driver",
                user = "admin", password = "1234")
        }
    }
}