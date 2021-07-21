package com.hosterdu.xenia.security

import com.hosterdu.xenia.profile.model.Profile
import com.hosterdu.xenia.profile.service.ProfileService
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.net.URI

@Component("oauth2authSuccessHandler")
class Oauth2AuthenticationSuccessHandler(
    private val profileService: ProfileService,
) : ServerAuthenticationSuccessHandler {


    override fun onAuthenticationSuccess(
        webFilterExchange: WebFilterExchange,
        authentication: Authentication
    ): Mono<Void> {
        val authenticationToken = authentication as OAuth2AuthenticationToken
        val attributes = authenticationToken.principal.attributes
        val profile = Profile(
            authentication.principal.name,
            attributes["email"].toString(),
            attributes["given_name"].toString(),
            attributes["family_name"].toString(),
            attributes["picture"].toString(),
            attributes["locale"].toString(),
        )
        profileService.findOrCreateProfile(profile)
        val response = webFilterExchange.exchange.response
        response.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
        response.getHeaders().setLocation(URI.create("/loginSuccess"));
        return response.setComplete();
    }
}
