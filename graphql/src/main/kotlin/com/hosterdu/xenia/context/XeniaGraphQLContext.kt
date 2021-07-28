package com.hosterdu.xenia.context

import com.expediagroup.graphql.server.spring.execution.SpringGraphQLContext
import com.expediagroup.graphql.server.spring.execution.SpringGraphQLContextFactory
import com.hosterdu.xenia.profile.model.Profile
import com.hosterdu.xenia.profile.service.ProfileService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import java.time.ZonedDateTime

class XeniaGraphQLContext(val profile: Profile?, request: ServerRequest) : SpringGraphQLContext(request)


@Component
class XeniaGraphQLContextFactory (val profileService: ProfileService): SpringGraphQLContextFactory<XeniaGraphQLContext>() {
    override suspend fun generateContext(request: ServerRequest): XeniaGraphQLContext {
        println(ZonedDateTime.now())
        val id =  ReactiveSecurityContextHolder.getContext() .map(SecurityContext::getAuthentication)
            .map(Authentication::getName).awaitSingle() ?: return XeniaGraphQLContext(null, request)
        val profile = profileService.findProfileById(id)
        return XeniaGraphQLContext(profile, request)
    }
}