package com.example.routes

import com.example.models.*
import com.example.security.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting(){
    route("/") {
        get{
            call.respondText("Hello")
        }
    }
}