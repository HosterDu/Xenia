package com.example.event.service

import com.example.event.model.Event
import com.example.user.repository.EventRepository
import com.example.util.Exception.UnauthorizedException
import com.example.util.Session.SessionHandler
import io.ktor.application.*

class EventService {
    private val eventRepository = EventRepository()

    suspend fun getAllUserEvents(call: ApplicationCall): List<Event> {
        val user = SessionHandler.getUserSession(call)?.user ?: throw UnauthorizedException()
        return eventRepository.getAllEventsByUser(user)
    }

    suspend fun createEvent(call: ApplicationCall, event: Event): Event {
        val user = SessionHandler.getUserSession(call)?.user ?: throw UnauthorizedException()
        return eventRepository.createEvent(event, user)
    }
}