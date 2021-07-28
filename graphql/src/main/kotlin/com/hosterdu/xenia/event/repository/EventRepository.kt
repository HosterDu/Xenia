package com.hosterdu.xenia.event.repository

import com.hosterdu.xenia.event.model.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository: JpaRepository<Event, String>
