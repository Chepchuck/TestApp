package com.example.models

import kotlinx.serialization.Serializable


@Serializable
data class MessageStatus (
    val read: Boolean,
    val notified: Boolean
)