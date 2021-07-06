package com.example.util.Session

import com.example.user.model.User
import com.example.util.OAuth.UserSession
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.sessions.*
import java.security.Principal

object SessionHandler {

    fun setUserSession(call: ApplicationCall, user: User, count: Int = 0) {
        call.sessions.set(UserSession(user = user, count = count))
    }

    fun getUserSession(call: ApplicationCall): UserSession? {
        return call.sessions.get<UserSession>()
    }
}
