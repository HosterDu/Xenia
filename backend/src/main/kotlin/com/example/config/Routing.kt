package com.example.config

import com.example.event.controller.event
import com.example.event.service.EventService
import com.example.user.controller.user
import com.example.user.service.UserService
import io.ktor.routing.*

fun Route.routing() {
    event(EventService())
    user(UserService())
}