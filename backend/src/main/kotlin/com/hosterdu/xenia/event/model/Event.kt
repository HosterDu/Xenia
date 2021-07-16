package com.hosterdu.xenia.event.model

import com.hosterdu.xenia.profile.model.Profile
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
data class Event(
    @GeneratedValue
    @Id
    val id: Long,
    @Lob
    val title: String,
    val description: String,
    val image: String,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime,
    @OneToOne
    @JoinColumn(name = "Profile", referencedColumnName = "id")
    val creator: Profile? = null,
    @OneToOne
    @JoinColumn(name = "Geolocation", referencedColumnName = "id")
    val location: Geolocation,
)
