package com.example.user.service

import com.example.user.model.User
import com.example.user.repository.UserRepository
import com.example.util.Exception.UnauthorizedException
import com.example.util.Session.SessionHandler
import io.ktor.application.*

class UserService {
    private val userRepository = UserRepository()

    suspend fun getLoggedInnUser(call: ApplicationCall): User {
        return SessionHandler.getUserSession(call)?.user ?: throw UnauthorizedException()
    }

}