package com.example.event.controller

import com.example.event.model.Event
import com.example.event.service.EventService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.event(eventService: EventService) {
    route("/events") {
        get {
            val events = eventService.getAllUserEvents(call)
            call.respond(HttpStatusCode.OK, events)
        }
        post {
            val response = call.receive<Event>()
            val event = eventService.createEvent(call, response)
            call.respond(HttpStatusCode.Created, event)
        }
    }
}