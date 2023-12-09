package com.example.models

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

@Serializable
data class Message (
    val text: String,
    val sender: User,
    val sendDate: LocalDate
)
//TODO: У сообщения не хватает времени отправки (исправлено)

object MessageTable : IntIdTable() {
    val text = varchar("text", 256)
    val chatId = reference("Chat_id", ChatTable.id)
    val senderId = reference("User_id", UserTable.id)
    val sendDate = date("Send date")
}