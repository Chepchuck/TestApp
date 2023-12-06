package com.example.routes

import com.example.models.*
import com.example.security.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting(){
    route("/user") {
        get{
            if (userStorage.isNotEmpty()){
                call.respondText(
                    "Users found",
                    status = HttpStatusCode.OK
                )
            }else{
                call.respondText("Users not found",
                    status = HttpStatusCode.OK
                )
            }
        }

        get("/user/{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val user = userStorage.find {it.id == id} ?: return@get call.respondText(
                "No users with id $id",
                status = HttpStatusCode.NotFound
            )
            user.hash = Hashing.getHash(user.pass.toByteArray(), user.coder)
            call.respondText("User with id - $id have password hash - ${user.hash}!")
        }
        post{
            val user = call.receive<User>()
            userStorage.add(user)
            call.respondText("User stored correctly", status = HttpStatusCode.Created)
        }
    }
}