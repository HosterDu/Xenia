package com.hosterdu.xenia.event.model

import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue

@Entity
data class Event(
    @GeneratedValue
    @Id
    val id: Long
)
