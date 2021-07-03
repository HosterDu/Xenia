package com.example.config.OAuth

import com.example.user.model.User

data class UserSession(val user: User, val count: Int)