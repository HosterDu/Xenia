package com.hosterdu.xenia.event

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import com.hosterdu.xenia.event.model.Event
import com.hosterdu.xenia.event.repository.EventRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureWebTestClient
class EventQueryTest  {
/*
    @Autowired
    private lateinit var testClient: WebTestClient

    lateinit var  event: Event

    @Autowired
    lateinit var eventRepository: EventRepository
    @BeforeEach
    fun setup() {
       event = Event(1, "heo", "dskjds", "mkdsds")
       event =  eventRepository.save(event)
    }

    @AfterEach
    fun cleanUp() {
        eventRepository.deleteAll()
    }
    @Test
    fun `verify contextualQuery query`() {
        val query = "event"

        testClient.post()
            .uri("graphql")
            .accept()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("query { $query(value: 1) { contextValue, passedInValue } }")
            .exchange()
            .expectStatus().isOk.expectBody()
            .jsonPath("data.$query.name").isEqualTo("hello")
            .jsonPath("data.$query.passedInValue").isEqualTo("1")
    }

    @Test
    fun `verify contextualQuery query with context value`() {
    }*/
}
