package com.hosterdu.xenia.security

import com.hosterdu.xenia.profile.service.ProfileService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient
import java.lang.Exception
import kotlin.Throws

@Configuration
class SecurityConfig(
    private val profileService: ProfileService,
) : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .oauth2Login()
            .permitAll()
            .successHandler(Oauth2AuthenticationSuccessHandler(profileService))

    }

}
