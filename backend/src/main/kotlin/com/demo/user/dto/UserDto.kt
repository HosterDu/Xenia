package com.demo.user.dto

import ntnu.idatt2105.user.model.User
import java.time.Instant.EPOCH
import java.time.LocalDate
import java.util.*

data class UserDto(
    val id: UUID = UUID.randomUUID(),
    val firstName: String = "",
    val surname: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val image: String = "",
)

fun User.toUserDto() = UserDto(id = this.id, firstName = this.firstName, surname = this.surname, email = this.email,
        phoneNumber = this.phoneNumber, image = this.image)
