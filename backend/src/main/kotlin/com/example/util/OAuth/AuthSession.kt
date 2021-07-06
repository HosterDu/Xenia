package com.example.util.OAuth

import com.example.user.model.User
import io.ktor.auth.*

data class UserSession(val user: User, val count: Int): Principal