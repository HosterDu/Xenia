package com.example.event.controller

import com.example.event.model.Event
import com.example.event.service.EventService
import io.ktor.application.*
import io.ktor.features.*
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
        get("/{eventId}") {
            val eventId = call.parameters["eventId"]!!
            val event = eventService.getEvent(call, eventId)

            if (event != null) {
                call.respond(HttpStatusCode.OK, event)
            } else {
                throw NotFoundException("Event not found")
            }
        }
        post {
            val response = call.receive<Event>()
            eventService.createEvent(call, response)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}
