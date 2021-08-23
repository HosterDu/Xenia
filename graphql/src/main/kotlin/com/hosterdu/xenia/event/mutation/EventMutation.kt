package com.hosterdu.xenia.event.mutation

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.execution.OptionalInput
import com.expediagroup.graphql.server.operations.Mutation
import com.hosterdu.xenia.context.XeniaGraphQLContext
import com.hosterdu.xenia.event.dto.CreateEventDto
import com.hosterdu.xenia.event.model.Event
import com.hosterdu.xenia.event.service.EventService
import org.springframework.stereotype.Component


@Component
class EventMutation(val eventService: EventService) : Mutation {

    /*This is possible, but i dont think is the best practice
    fun event(id: OptionalInput<Int>, event: CreateEventDto, context: XeniaGraphQLContext): Event = when(id){
        is OptionalInput.Undefined -> eventService.createEvent(event, context.profile)
        is OptionalInput.Defined<Int> -> eventService.updateEvent(id.value!!, event, context.profile)
    } */

    fun createEvent(event: CreateEventDto, context: XeniaGraphQLContext) = eventService.createEvent(event, context.profile)
    fun updateEvent(id: String, event: CreateEventDto, context: XeniaGraphQLContext) = eventService.updateEvent(id,    event, context.profile)

}