package com.hosterdu.xenia.event.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Geolocation(
    @Id
    @Column(name="id")
    val id: String,
    val address: String,
    val lat: Float,
    val lng: Float,
)