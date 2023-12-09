package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table


@Serializable
data class MessageStatus (
    val read: Boolean,
    val notified: Boolean
)

object MessageStatusTable : Table() {
    val messageId = reference("Message_id", MessageTable.id)
    val userId = reference("User_id", UserTable.id)
    val read = bool("Read")
    val notified = bool("Notified")
}