package com.hosterdu.xenia.util

import com.expediagroup.graphql.server.operations.Query
import com.hosterdu.xenia.event.service.EventService
import com.hosterdu.xenia.profile.service.ProfileService
import org.springframework.stereotype.Component

@Component
class SearchQuery(val profileService: ProfileService, val eventService: EventService): Query {

    fun search(query: String): List<Node>{
        val profiles = profileService.getAllProfiles()
        val events = eventService.gettAllEvents()
        val response = profiles + events
        return response

    }
}