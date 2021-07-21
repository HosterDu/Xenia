package com.hosterdu.xenia.event.dto

import com.hosterdu.xenia.event.model.Event
import com.hosterdu.xenia.event.model.Geolocation
import java.time.ZonedDateTime
import javax.persistence.*

data class CreateEventDto (
    val title: String,
    @Column(columnDefinition = "text")
    val description: String,    
    val image: String,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime,
    val lat: Long,
    val lng: Long,
    )

fun CreateEventDto.toEvent() = Event(
    id = "",
    title = this.title,
    description = this.description,
    image = this.image,
    startDate = this.startDate,
    endDate = this.endDate,
    location = Geolocation("",lat = this.lat, lng = this.lng)
)
