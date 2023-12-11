package com.example.data

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object MessageTable : IntIdTable() {
    val text = varchar("text", 256)
    val chatId = reference("Chat_id", ChatTable.id)
    val senderId = reference("User_id", UserTable.id)
    val sendDate = datetime("Send date")
}