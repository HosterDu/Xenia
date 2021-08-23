package com.hosterdu.xenia.event


import com.hosterdu.xenia.DATA_JSON_PATH
import com.hosterdu.xenia.GRAPHQL_ENDPOINT
import com.hosterdu.xenia.GRAPHQL_MEDIA_TYPE
import com.hosterdu.xenia.event.dto.toCreateEventDto
import com.hosterdu.xenia.event.model.Event
import com.hosterdu.xenia.event.model.Geolocation
import com.hosterdu.xenia.event.repository.EventRepository
import com.hosterdu.xenia.event.repository.GeolocationRepository
import com.hosterdu.xenia.factories.EventFactory
import com.hosterdu.xenia.utils.GraphqlTestUtils
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.context.support.WithUserDetails


@SpringBootTest
@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EventMutationTest {

    @Autowired
    private lateinit var testClient: WebTestClient

    lateinit var  event: Event

    @Autowired
    lateinit var geolocationRepository : GeolocationRepository


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
    fun `verify createEvent mutation`() {
        val query = "createEvent"
        val newEvent = EventFactory().`object`
        testClient.post()
            .uri(GRAPHQL_ENDPOINT)
            .accept(APPLICATION_JSON)
            .contentType(GRAPHQL_MEDIA_TYPE)
            .bodyValue(graphqlTestUtils.getMutation(Event::class.java, queryName = query, inputName = "event", input =newEvent.toCreateEventDto() ))
            .exchange()
            .expectStatus().isOk.expectBody()
            .jsonPath("${DATA_JSON_PATH}.${query}.description").isEqualTo(newEvent.description)
            .jsonPath("${DATA_JSON_PATH}.${query}.title").isEqualTo(newEvent.title)
    }

    @Test
    @WithMockUser
    fun `verify createEvent mutation creates geolocation`() {
        val query = "createEvent"
        val newEvent = EventFactory().`object`
        testClient.post()
            .uri(GRAPHQL_ENDPOINT)
            .accept(APPLICATION_JSON)
            .contentType(GRAPHQL_MEDIA_TYPE)
            .bodyValue("mutation { $query(event:${newEvent} ) { location { id lat lng } } }")
            .exchange()
            .expectStatus().isOk.expectBody()
            .jsonPath("${DATA_JSON_PATH}.${query}.location.lng").isEqualTo(newEvent.location!!.lng)
            .jsonPath("${DATA_JSON_PATH}.${query}.location.lat").isEqualTo(newEvent.location!!.lat)
    }

    @Test
    @WithUserDetails
    fun `verify createEvent mutation adds creator`() {
        val query = "createEvent"
        val newEvent = EventFactory().`object`
        testClient.post()
            .uri(GRAPHQL_ENDPOINT)
            .accept(APPLICATION_JSON)
            .contentType(GRAPHQL_MEDIA_TYPE)
            .bodyValue("mutation { $query(event:${newEvent} ) { creator { id  } } }")
            .exchange()
            .expectStatus().isOk.expectBody()
            .jsonPath("${DATA_JSON_PATH}.${query}.creator.id").isNotEmpty
    }
}
