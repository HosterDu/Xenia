package com.hosterdu.xenia.repository.event

import com.hosterdu.xenia.model.event.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository: JpaRepository<Event, Long>
