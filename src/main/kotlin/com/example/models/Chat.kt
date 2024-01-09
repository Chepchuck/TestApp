package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    val name: String,
    val participants: List<User>
)