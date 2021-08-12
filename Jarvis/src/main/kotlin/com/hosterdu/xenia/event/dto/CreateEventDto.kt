package com.hosterdu.xenia.event.dto

import com.hosterdu.xenia.event.model.Event
import java.time.ZonedDateTime
import javax.persistence.*

data class CreateEventDto (
    val title: String,
    @Column(columnDefinition = "text")
    val description: String,    
    val picture: String,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime,
    val location: GeoLocationDto
    )

fun CreateEventDto.toEvent() = Event(
    id = "",
    title = this.title,
    description = this.description,
    picture = this.picture,
    startDate = this.startDate,
    endDate = this.endDate,
    location = this.location.toGeoLocation()
)
