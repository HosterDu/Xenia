package com.hosterdu.xenia.event.service

import com.hosterdu.xenia.context.XeniaGraphQLContext
import com.hosterdu.xenia.event.dto.CreateEventDto
import com.hosterdu.xenia.event.dto.toEvent
import com.hosterdu.xenia.event.model.Event
import com.hosterdu.xenia.event.repository.EventRepository
import com.hosterdu.xenia.event.repository.GeolocationRepository
import com.hosterdu.xenia.profile.model.Profile
import com.hosterdu.xenia.profile.service.ProfileService
import org.springframework.stereotype.Service

@Service
class EventService(val eventRepository: EventRepository, val geolocationRepository: GeolocationRepository) {

    fun createEvent(event : CreateEventDto, profile: Profile?) : Event{
        val newEvent = event.toEvent()
        newEvent.creator = profile
        if(newEvent.location != null){
            val geolocation = newEvent.location
           newEvent.location = geolocationRepository.saveAndFlush(geolocation!!)

        }
        return eventRepository.saveAndFlush(newEvent)

    }

    fun updateEvent(id:String, event : CreateEventDto, profile: Profile?): Event {
        findById(id).run {
            var updatedEvent = this.copy(
                title = event.title,
                description = event.description,
                image = event.image
            )
            updatedEvent = eventRepository.saveAndFlush(updatedEvent)
            return updatedEvent
        }
    }


    fun findById(id: String): Event{
        return eventRepository.findById(id).orElseThrow()
    }
    fun gettAllEvents() = eventRepository.findAll()
}