package com.example.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Message (
    val text: String,
    val sender: User,
    val sendDate: Instant
)