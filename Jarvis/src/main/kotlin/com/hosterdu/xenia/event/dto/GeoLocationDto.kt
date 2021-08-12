package com.hosterdu.xenia.event.dto

import com.hosterdu.xenia.event.model.GeoLocationType
import com.hosterdu.xenia.event.model.Geolocation

data class GeoLocationDto(
    val id: String?,
    val contextualName: String,
    val type: GeoLocationType?,
    val lat: Long?,
    val lng: Long?,
)


fun GeoLocationDto.toGeoLocation() = Geolocation(
    id = "",
    contextualName = this.contextualName,
    type = this.type,
    lat = this.lat,
    lng = this.lng
)