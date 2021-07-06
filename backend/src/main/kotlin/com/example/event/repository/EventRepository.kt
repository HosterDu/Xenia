package com.example.user.repository

import com.example.config.Database
import com.example.event.model.Event
import com.example.event.model.Events
import com.example.user.model.User
import com.example.util.Utils
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
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
            picture = row[Events.picture],
            createdById = row[Events.createdById],
        )

    suspend fun getAllEventsByUser(user: User): List<Event> {
        return Database.dbQuery {
            return@dbQuery Events
                .select { Events.createdById eq user.id }
                .map { toEvent(it) }
        }
    }

    suspend fun getEvent(user: User, event_id: String): Event? {
        return Database.dbQuery {
            return@dbQuery Events
                .select { Events.createdById eq user.id and (Events.id eq event_id) }
                .mapNotNull { toEvent(it) }
                .singleOrNull()
        }
    }

    suspend fun createEvent(event: Event, user: User) {
        try {
            val a =  Database.dbQuery {
                return@dbQuery (Events.insert {
                    it[id] = Utils.generateId()
                    it[title] = event.title
                    it[description] = event.description!!
                    it[start_date_time] = event.start_date_time
                    it[location] = event.location!!
                    it[picture] = event.picture!!
                    it[createdById] = user.id
                }.resultedValues)
            }
            println(a.toString())
        } catch (e: ExposedSQLException) {
            println(e)
            throw Exception("Not created event")
        }
    }
}
