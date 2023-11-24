package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class User (val id: String, val login: String, val pass: String, val coder: String, var hash: String? = null)
val userStorage = mutableListOf<User>()