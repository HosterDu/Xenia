package com.hosterdu.xenia.event

import com.hosterdu.xenia.DATA_JSON_PATH
import com.hosterdu.xenia.GRAPHQL_ENDPOINT
import com.hosterdu.xenia.GRAPHQL_MEDIA_TYPE
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import com.hosterdu.xenia.event.model.Event
import com.hosterdu.xenia.event.model.Geolocation
import com.hosterdu.xenia.event.repository.EventRepository
import com.hosterdu.xenia.event.repository.GeolocationRepository
import com.hosterdu.xenia.factories.EventFactory
import com.hosterdu.xenia.utils.GraphqlTestUtils
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EventQueryTest  {

    @Autowired
    private lateinit var testClient: WebTestClient

    lateinit var  event: Event

    @Autowired
    lateinit var geolocationRepository: GeolocationRepository

    val graphqlTestUtils = GraphqlTestUtils()

    @Autowired
    lateinit var eventRepository: EventRepository

    @BeforeEach
    fun setup() {
        event = EventFactory().`object`
        event.location = geolocationRepository.save(event.location!!)
        event =  eventRepository.save(event)
    }


    @AfterEach
    fun cleanUp() {
        eventRepository.deleteAll()
    }

    @Test
    @WithMockUser
    fun `verify event by id query`() {
        val query = "event"
        testClient.post()
                .uri(GRAPHQL_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(GRAPHQL_MEDIA_TYPE)
                .bodyValue(graphqlTestUtils.getQuery(Event::class.java, queryName = query, params = hashMapOf("id" to Pair(event.id, true))))
                .exchange()
                .expectStatus().isOk.expectBody()
                .jsonPath("${DATA_JSON_PATH}.${query}.id").isEqualTo(event.id)
                .jsonPath("${DATA_JSON_PATH}.${query}.title").isEqualTo(event.title)
    }

    @Test
    @WithMockUser
    fun `verify events query` () {
        val query = "events"

        testClient.post()
                .uri(GRAPHQL_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(GRAPHQL_MEDIA_TYPE)
                .bodyValue(graphqlTestUtils.getQuery(Event::class.java, query))
                .exchange()
                .expectStatus().isOk.expectBody()
                .jsonPath("${DATA_JSON_PATH}.${query}.[*].id").isEqualTo(event.id)
                .jsonPath("${DATA_JSON_PATH}.${query}.[*].title").isEqualTo(event.title)
    }

    @Test
    @WithMockUser
    fun `verify events query get location` () {
        val query = "events"

        testClient.post()
                .uri(GRAPHQL_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(GRAPHQL_MEDIA_TYPE)
                .bodyValue(graphqlTestUtils.getQuery(Event::class.java, query, subQueries = listOf(Geolocation::class.java)))
                .exchange()
                .expectStatus().isOk.expectBody()
                .jsonPath("${DATA_JSON_PATH}.${query}.[*].location.lng").isEqualTo(event.location!!.lng.toString())
                .jsonPath("${DATA_JSON_PATH}.${query}.[*].location.lat").isEqualTo(event.location!!.lat.toString())
    }
}