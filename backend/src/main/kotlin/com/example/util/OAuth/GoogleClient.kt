package com.example.util.OAuth

import io.ktor.auth.*
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

@Serializable
data class GoogleUserProfile (
    val sub: String,
    val name: String,
    val given_name: String,
    val family_name: String,
    val picture: String,
    val email: String,
    val email_verified: Boolean,
)

object GoogleClient {
    fun getUserProfile(principal: OAuthAccessTokenResponse.OAuth2): GoogleUserProfile {
        val url = "https://www.googleapis.com/oauth2/v3/userinfo"
        return runBlocking {
            return@runBlocking HttpClient() {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }
            }
                .get<GoogleUserProfile>(url) {
                    accept(ContentType.Application.Json)
                    headers {
                        append(HttpHeaders.Authorization, "Bearer ${principal.accessToken}")
                    }
                }
        }
    }
}
