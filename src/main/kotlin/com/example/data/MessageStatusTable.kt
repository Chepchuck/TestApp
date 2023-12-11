package com.example.data

import org.jetbrains.exposed.sql.Table

object MessageStatusTable : Table() {
    val messageId = reference("Message_id", MessageTable.id)
    val userId = reference("User_id", UserTable.id)
    val read = bool("Read")
    val notified = bool("Notified")
}