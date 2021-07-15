package com.hosterdu.xenia.event.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Geolocation(
        @GeneratedValue
        @Id
        val id: Long,
        val lat: Long,
        val lng: Long,
        )
