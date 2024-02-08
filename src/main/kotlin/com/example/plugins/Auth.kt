package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.User
import com.example.repository.UserRepository
import com.example.security.Hashing.getHash
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*


fun Application.configureAuth() {
    val secret = "secret" //environment.config.property("jwt.secret").getString()
    val issuer = "http://0.0.0.0:8080/" //environment.config.property("jwt.issuer").getString()
    val audience = "http://0.0.0.0:8080/" //environment.config.property("jwt.audience").getString()
    val myRealm = "local host" //environment.config.property("jwt.realm").getString()

    // Please read the jwt property from the config file if you are using EngineMain
    install(Authentication) {
        jwt("auth-jwt"){
            realm = myRealm
            verifier(JWT
                .require(Algorithm.HMAC256(secret))
                .withAudience(audience)
                .withIssuer(issuer)
                .build()
            )
            challenge{defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}
