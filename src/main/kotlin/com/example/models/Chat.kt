package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
data class Chat(
    val name: String,
    val participants: List<User>
 // Я бы добавила список участников чата, также необходима промежуточная таблица для связей
 /**Как я понимаю, промежуточная таблица необходима для связи Users и Chats, возможно ли эту
  * связь реализовать через таблицу Messages?*/
)

object ChatTable : IntIdTable(){
    val name = varchar("name", 20)
}