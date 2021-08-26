package com.hosterdu.xenia.profile

import com.hosterdu.xenia.DATA_JSON_PATH
import com.hosterdu.xenia.GRAPHQL_ENDPOINT
import com.hosterdu.xenia.GRAPHQL_MEDIA_TYPE
import com.hosterdu.xenia.factories.ProfileFactory
import com.hosterdu.xenia.profile.model.Profile
import com.hosterdu.xenia.profile.repository.ProfileRepository
import com.hosterdu.xenia.utils.GraphqlTestUtils
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.*
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication


@SpringBootTest
@AutoConfigureWebTestClient
@TestInstance(PER_CLASS)
class ProfileQueryTest(@Autowired private  var testClient: WebTestClient,
                       @Autowired var profileRepository: ProfileRepository) {


    lateinit var  profile: Profile

    val profileFactory = ProfileFactory()

    @BeforeEach
    fun setup() {
        profile = profileFactory.`object`
        profile =  profileRepository.save(profile)
    }

    @Test
    fun `verify contextualQuery query`() {
        val query = "profile"
        print(profile.id)
        testClient.mutateWith(mockOAuth2Client()
                .clientRegistration(ClientRegistration
                        .withRegistrationId("google").authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE).build())).post()
                .uri(GRAPHQL_ENDPOINT)
                .accept(APPLICATION_JSON)
                .contentType(GRAPHQL_MEDIA_TYPE)
                .bodyValue(GraphqlTestUtils().getQuery(Profile::class.java, query))
                .exchange()
                .expectStatus().isOk.expectBody()
                .jsonPath("$DATA_JSON_PATH.${query}.given_name").isEqualTo(profile.given_name)
    }
}