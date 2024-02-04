package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentials(val login: String, val password: String)