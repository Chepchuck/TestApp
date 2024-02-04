package com.example.data

import org.jetbrains.exposed.dao.id.UUIDTable

object UserTable : UUIDTable(){ // Для удобства можно наследоваться от класса UUIDTable() - первичный ключ будет генерироваться фреймворком самостоятельно
    val login = varchar("Login", 30).uniqueIndex()
    val firstName = varchar("First Name", 30) // Почему var'ы? (Исправлено, перепутал)
    val lastName = varchar("Last Name", 30)
    val passHash = varchar("Password hash", 64)
}