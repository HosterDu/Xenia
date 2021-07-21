package com.hosterdu.xenia.security



import com.hosterdu.xenia.profile.model.Profile
import com.hosterdu.xenia.profile.service.ProfileService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
class SecurityController(
    private val profileService: ProfileService,
){

    @GetMapping("/loginSuccess")
    fun getLoginInfo(authentication: OAuth2AuthenticationToken, @RegisteredOAuth2AuthorizedClient client: OAuth2AuthorizedClient): Pair<OAuth2AuthenticationToken, OAuth2AuthorizedClient> {


        return Pair(authentication, client)
    }

    @GetMapping("/check")
    fun check(principal: Principal ): Profile? {
        return profileService.findProfileById(principal.name)
    }
}
