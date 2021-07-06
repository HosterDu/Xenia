package com.example.user.controller

import com.example.user.service.UserService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.user(userService: UserService) {
    route("/users") {
        get {
            val user = userService.getLoggedInnUser(call)
            call.respond(HttpStatusCode.OK, user)
        }
    }
}