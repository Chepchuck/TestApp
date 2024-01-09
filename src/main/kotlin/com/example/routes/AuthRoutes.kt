package com.example.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.authRouting() {
    authenticate("auth-basic"){
        get("/api/auth/login"){
            call.respondText("Hello, ${call.principal<UserIdPrincipal>()?.name}!")
        }
    }
}