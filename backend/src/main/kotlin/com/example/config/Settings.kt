package com.example.config

import com.example.config.OAuth.UserSession
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.locations.*
import io.ktor.serialization.*
import io.ktor.sessions.*

fun Application.settings() {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("Xenia")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }
    install(Locations)
    install(ContentNegotiation) {
        json()
    }
    install(DefaultHeaders)
    install(CallLogging)
    install(Sessions) {
        cookie<UserSession>("user_session")
    }
}