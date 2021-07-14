package com.hosterdu.xenia.security

import com.hosterdu.xenia.event.repository.EventRepository
import com.hosterdu.xenia.profile.model.Profile
import com.hosterdu.xenia.profile.service.ProfileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component("oauth2authSuccessHandler")
class Oauth2AuthenticationSuccessHandler(
    private val profileService: ProfileService,
) : AuthenticationSuccessHandler {
    private val redirectStrategy: RedirectStrategy = DefaultRedirectStrategy()

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationSuccess(
        request: HttpServletRequest, response: HttpServletResponse,
        authentication: Authentication
    ) {
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
        redirectStrategy.sendRedirect(request, response, "/loginSuccess")
    }
}
