package com.example.user.repository

import com.example.config.Database
import com.example.event.model.Event
import com.example.event.model.Events
import com.example.user.model.User
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.lang.Exception
import java.util.*

class EventRepository {
    private fun toEvent(row: ResultRow): Event =
        Event(
            id = row[Events.id],
            title = row[Events.title],
            description = row[Events.description],
            start_date_time = row[Events.start_date_time],
            location = row[Events.location],
            createdById = row[Events.createdById],
        )

    suspend fun getAllEventsByUser(user: User): List<Event> {
        return Database.dbQuery {
            return@dbQuery Events
                .select { Events.createdById eq user.id }
                .map { toEvent(it) }
        }
    }

    suspend fun createEvent(event: Event, user: User): Event {
        val eventId = UUID.randomUUID()
        try {
            return Database.dbQuery {
                Events.insert {
                    it[id] = eventId
                    it[title] = event.title
                    it[description] = event.description!!
                    it[start_date_time] = event.start_date_time
                    it[location] = event.location!!
                    it[createdById] = user.id
                }
                return@dbQuery Events.select {
                    Events.id eq eventId
                }
                    .map { toEvent(it) }
                    .single()
            }
        } catch (e: ExposedSQLException) {
            println(e)
            throw Exception("Not created event")
        }
    }
}