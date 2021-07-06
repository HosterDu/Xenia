package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.config.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        settings()
        oAuth()
        Database.init()
    }.start(wait = true)
}
