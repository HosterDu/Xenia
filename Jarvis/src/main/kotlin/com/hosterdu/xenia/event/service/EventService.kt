package com.hosterdu.xenia.event.service

import com.hosterdu.xenia.context.XeniaGraphQLContext
import com.hosterdu.xenia.event.dto.CreateEventDto
import com.hosterdu.xenia.event.dto.toEvent
import com.hosterdu.xenia.event.model.Event
import com.hosterdu.xenia.event.model.Geolocation
import com.hosterdu.xenia.event.repository.EventRepository
import com.hosterdu.xenia.event.repository.GeolocationRepository
import com.hosterdu.xenia.profile.model.Profile
import com.hosterdu.xenia.profile.service.ProfileService
import com.hosterdu.xenia.util.UnauthorizedException
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.server.ResponseStatusException

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
            if(this.creator?.id != profile?.id) {
                throw UnauthorizedException()
            }
            var updatedEvent = this.copy(
                title = event.title,
                description = event.description,
                picture = event.picture,
                startDate = event.startDate,
                endDate = event.endDate,
                location = Geolocation(id = event.locationId,lat = event.lat, lng = event.lng, address = event.address)
            )
            if(updatedEvent.location != null){
                val geolocation = updatedEvent.location
                updatedEvent.location = geolocationRepository.saveAndFlush(geolocation!!)

            }
            return  eventRepository.saveAndFlush(updatedEvent)
        }
    }

    fun deleteEvent(id: String, profile: Profile?): String {
        findById(id).run {
            if (this.creator?.id != profile?.id) {
                throw UnauthorizedException()
            }
            eventRepository.delete(this)
            return id
        }
    }

    fun findById(id: String): Event{
        return eventRepository.findById(id).orElseThrow()
    }
    fun getAllEvents(): MutableList<Event> = eventRepository.findAll()
}