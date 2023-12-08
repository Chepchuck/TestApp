package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
data class Message (
    val id: Int,
    val text: String,
    val chatId: Int,
    val senderId: String
)
// TODO: У сообщения не хватает времени отправки

object Messages : IntIdTable(){
    val text = varchar("text", 256)
    val chatId = integer("chatId")
        .uniqueIndex() // почему uniqueIndex, а не unique?
        .references(Chats.id)
    val senderId = varchar("senderId", 36)
        .uniqueIndex()
        .references(Users.uuid)
}