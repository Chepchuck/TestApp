package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class MessageStatus (
    val messageId: Int,
    val userId: String,
    val read: Boolean,
    val notified: Boolean
)

object MessageStatuses : Table() {
    val messageId = integer("Message Id")
        .uniqueIndex()
        .references(Messages.id)
    val userId = varchar("User UUID", 36)
        .uniqueIndex()
        .references(Users.uuid)
    val read = bool("Read")
    val notified = bool("Notified")
}