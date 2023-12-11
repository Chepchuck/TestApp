package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val login: String,
    var firstName: String,
    var lastName: String,
    var passHash: String,
    var online: Boolean
)