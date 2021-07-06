package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.config.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt(), "0.0.0.0") {
        settings()
        oAuth()
        Database.init()
    }.start(wait = true)
}
