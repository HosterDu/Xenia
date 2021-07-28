package com.hosterdu.xenia.security;

import com.hosterdu.xenia.profile.service.ProfileService
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


@EnableWebFluxSecurity
class SecurityConfig(val profileService: ProfileService){
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.csrf().disable()
            .authorizeExchange()
            .pathMatchers(HttpMethod.POST,"/graphql").permitAll()
            .anyExchange().authenticated()
            .and().oauth2Login().authenticationSuccessHandler(Oauth2AuthenticationSuccessHandler(profileService))
            .and().build();
    }
}
