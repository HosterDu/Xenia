package com.hosterdu.xenia.event.query

import com.expediagroup.graphql.server.operations.Query
import com.hosterdu.xenia.event.repository.EventRepository
import com.hosterdu.xenia.event.service.EventService
import org.springframework.stereotype.Component

@Component
class EventQuery (val eventService: EventService): Query {
    fun  event(id :String) = eventService.findById(id)
    fun events() = eventService.gettAllEvents()


}