package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

@Serializable
data class Chat(
    val id: Int,
    val name: String
)

object Chats : IntIdTable(){
    val name = varchar("name", 20)
}