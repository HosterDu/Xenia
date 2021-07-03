package com.example.config

import com.example.event.controller.event
import com.example.event.service.EventService
import io.ktor.routing.*

fun Route.routing() {
    route("/api") {
        event(EventService())
    }
}