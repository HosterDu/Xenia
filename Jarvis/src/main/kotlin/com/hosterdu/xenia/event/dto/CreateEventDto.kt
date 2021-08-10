package com.hosterdu.xenia.event.dto

import com.hosterdu.xenia.event.model.Event
import com.hosterdu.xenia.event.model.Geolocation
import java.time.ZonedDateTime
import javax.persistence.*

data class CreateEventDto (
    val title: String,
    @Column(columnDefinition = "text")
    val description: String,    
    val picture: String,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime,
    val lat: Float,
    val lng: Float,
    val address: String,
    val locationId: String
    )

fun CreateEventDto.toEvent() = Event(
    id = "",
    title = this.title,
    description = this.description,
    picture = this.picture,
    startDate = this.startDate,
    endDate = this.endDate,
    location = Geolocation(id = this.locationId,lat = this.lat, lng = this.lng, address = this.address)
)
