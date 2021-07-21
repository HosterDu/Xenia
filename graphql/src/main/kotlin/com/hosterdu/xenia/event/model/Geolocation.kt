package com.hosterdu.xenia.event.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Geolocation(
    @Id
    @GenericGenerator(name = "Nano_id", strategy = "com.hosterdu.xenia.util.IdGenerator")
    @GeneratedValue(generator = "Nano_id")
    @Column(name="id")
    val id: String?,
    val lat: Long,
    val lng: Long,
)