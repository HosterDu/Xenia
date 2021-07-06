package com.example.config

import com.example.util.Exception.UnauthorizedException
import com.example.util.OAuth.UserSession
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.sessions.*

fun Application.settings() {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.AccessControlAllowHeaders)
        header(HttpHeaders.ContentType)
        header(HttpHeaders.AccessControlAllowOrigin)
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
    install(StatusPages) {
        exception<UnauthorizedException> {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
    install(Routing) {
        routing()
    }
}
