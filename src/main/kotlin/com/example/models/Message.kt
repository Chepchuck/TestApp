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

object Messages : IntIdTable(){
    val text = varchar("text", 256)
    val chatId = integer("chatId")
        .uniqueIndex()
        .references(Chats.id)
    val senderId = varchar("senderId", 36)
        .uniqueIndex()
        .references(Users.uuid)
}