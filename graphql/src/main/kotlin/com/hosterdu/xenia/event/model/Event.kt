package com.hosterdu.xenia.event.model

import com.hosterdu.xenia.profile.model.Profile
import com.hosterdu.xenia.util.Node
import org.hibernate.annotations.GenericGenerator
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
data class Event(
    @Id
    @GenericGenerator(name = "Nano_id", strategy = "com.hosterdu.xenia.util.IdGenerator")
    @GeneratedValue(generator = "Nano_id")
    @Column(name="id")
    val id: String,
    val title: String,
    @Column(columnDefinition = "text")
    val description: String,
    val image: String,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime,
    @OneToOne
    @JoinColumn(name = "Profile", referencedColumnName = "id")
    var creator: Profile? = null,
    @OneToOne
    @JoinColumn(name = "Geolocation", referencedColumnName = "id")
    var location: Geolocation? = null,
) : Node