package com.example.plugins

import com.example.dao.DatabaseSingleton
import com.example.security.digestFunction
import io.ktor.server.application.*
import io.ktor.server.auth.*
val hashedUserTable = UserHashedTableAuth(
    table = DatabaseSingleton.selectAuthInfo(),
    digester = digestFunction
)

fun Application.configureAuth() {
    authentication {
        basic("auth-basic") {
            realm = "Ktor Server"
            validate { credentials ->
                hashedUserTable.authenticate(credentials)
            }
        }
    }
}