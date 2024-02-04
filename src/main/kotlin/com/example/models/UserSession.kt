package com.example.models

data class UserSession(var token: String?)
val userSession = mutableListOf<UserSession>()