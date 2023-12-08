package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import java.util.UUID

@Serializable
data class User(
    val uuid: String = UUID.randomUUID().toString(), // Зачем в модели Id из БД?
    val login: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    val hash: String = "md5", // TODO Хэширование - одна из утилитарных функций в приложении - не должна быть частью доменной модели пользователя
    var passHash: String? = null // Зачем 2 пароля в одной модели? Захешированный и в обычном виде
)

object Users : Table(){ // Для удобства можно наследоваться от класса UUIDTable() - первичный ключ будет генерироваться фреймворком самостоятельно
    val uuid = varchar("UUID", 36)
    val login = varchar("Login", 30)
    var firstName = varchar("First Name", 30) // Почему var'ы?
    var lastName = varchar("Last Name", 30)
    var passHash = varchar("Password hash", 36)
    override val primaryKey = PrimaryKey(uuid, name = "PK_User_UUID")
}