package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import java.util.UUID

@Serializable
data class User(
    var uuid: String = UUID.randomUUID().toString(),
    val login: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    var hash: String = "md5",
    val passHash: String? = null
)

object Users : Table(){
    val uuid = varchar("uuid", 36)
    val login = varchar("login", 20)
    var firstName = varchar("firstName", 30)
    var lastName = varchar("lastName", 30)
    var passHash = varchar("passHash", 36)
    override val primaryKey = PrimaryKey(uuid, name = "PK_User_UUID")
}