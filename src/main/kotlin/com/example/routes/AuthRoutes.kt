package com.example.routes

import com.example.models.*
import com.example.services.AuthService
import com.example.utils.TokenManager
import com.typesafe.config.ConfigFactory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.authRouting(authService: AuthService) {
    route("/api/auth") {
        val tokenManager = TokenManager(HoconApplicationConfig(ConfigFactory.load()))

        post("/registration"){
            val user = call.receive<User>()
            if (!authService.reg(user)){
                call.respond(HttpStatusCode.Conflict, hashMapOf("status" to "Пользователь с таким логином уже существует"))
            }
            call.respond(HttpStatusCode.OK, hashMapOf("status" to "Registration successful"))
        }

        post("/login") {
            val userCredentials = call.receive<UserCredentials>()
            if (!authService.login(userCredentials)){
                call.respond(HttpStatusCode.NotFound, mapOf("status" to "Please check login or password"))
            }
            val token = tokenManager.generateJWTToken(userCredentials)
            userSession.add(UserSession(token))
            call.respond(hashMapOf("token" to token, "status" to HttpStatusCode.OK.toString()))
        }
        authenticate("auth-jwt") {
            post("/logout") {
                val tokenPrincipal = call.authentication.principal<JWTPrincipal>()
                val user = tokenPrincipal?.payload?.getClaim("login")?.asString()
                call.respondText("User $user logged out successfully")
            }
        }

        post("/check-token"){
            val principal = call.principal<JWTPrincipal>()
            if (principal?.payload?.getClaim("login").toString() != "" && principal?.expiresAt?.time?.minus(System.currentTimeMillis()) != null){
                call.respond(HttpStatusCode.OK, hashMapOf("status" to "OK"))
            }
            call.respond(HttpStatusCode.Unauthorized, hashMapOf("status" to "Token is not valid or has expired"))
        }
    }
}