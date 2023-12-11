package com.example.data

import org.jetbrains.exposed.sql.Table

object ChatParticipantsTable : Table(){
    val userId = reference("User_id", UserTable.id)
    val chatId = reference("Chat_id", ChatTable.id)
}