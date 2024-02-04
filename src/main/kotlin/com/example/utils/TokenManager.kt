package com.example.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.UserCredentials
import com.example.models.UserSession
import io.ktor.server.config.*
import java.util.*

class TokenManager(private val config: HoconApplicationConfig) {
    fun generateJWTToken(userCredentials: UserCredentials) : String{
        val audience = config.property("jwt.audience").getString()
        val secret = config.property("jwt.secret").getString()
        val issuer = config.property("jwt.issuer").getString()
        val expirationDate = System.currentTimeMillis() + 600000

        val token = JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("login", userCredentials.login)
            .withExpiresAt(Date(expirationDate))
            .sign(Algorithm.HMAC256(secret))

        return token
    }

    fun clearToken(userSession: UserSession){

    }
}