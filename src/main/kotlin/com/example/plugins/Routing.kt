package com.example.plugins

import com.example.routes.authRouting
import com.example.routes.userRouting
import com.example.services.AuthService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        userRouting()
        authRouting(AuthService())
    }
}
