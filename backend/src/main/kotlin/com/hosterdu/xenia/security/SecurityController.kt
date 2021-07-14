package com.hosterdu.xenia.security


import com.hosterdu.xenia.profile.model.Profile
import com.hosterdu.xenia.profile.service.ProfileService
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
class SecurityController(
    private val authorizedClientService: OAuth2AuthorizedClientService,
    private val profileService: ProfileService,
){

    @GetMapping("/loginSuccess")
    fun getLoginInfo(authentication: OAuth2AuthenticationToken): Pair<OAuth2AuthenticationToken, OAuth2AuthorizedClient> {
        val client: OAuth2AuthorizedClient = authorizedClientService
            .loadAuthorizedClient(
                authentication.authorizedClientRegistrationId,
                authentication.name
            )
        return Pair(authentication, client)
    }

    @GetMapping("/check")
    fun check(principal: Principal ): Profile? {
        return profileService.findProfileById(principal.name)
    }
}
