package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import java.util.UUID

@Serializable
data class User(
    val uuid: String = UUID.randomUUID().toString(),
    val login: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    val hash: String = "md5",
    var passHash: String? = null
)

object Users : Table(){
    val uuid = varchar("UUID", 36)
    val login = varchar("Login", 30)
    var firstName = varchar("First Name", 30)
    var lastName = varchar("Last Name", 30)
    var passHash = varchar("Password hash", 36)
    override val primaryKey = PrimaryKey(uuid, name = "PK_User_UUID")
}