package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.UUIDTable

@Serializable
data class User(
    val login: String,
    var firstName: String,
    var lastName: String,
    var passHash: String,
    var online: Boolean
)

object UserTable : UUIDTable(){ // Для удобства можно наследоваться от класса UUIDTable() - первичный ключ будет генерироваться фреймворком самостоятельно
    val login = varchar("Login", 30)
    val firstName = varchar("First Name", 30) // Почему var'ы? (Исправлено, перепутал)
    val lastName = varchar("Last Name", 30)
    val passHash = varchar("Password hash", 36)
}