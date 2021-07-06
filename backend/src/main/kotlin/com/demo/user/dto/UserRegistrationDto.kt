package com.demo.user.dto

import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class UserRegistrationDto(
    @get:NotBlank(message = "Field must not be blank")
    val firstName: String,
    @get:NotBlank(message = "Field must not be blank")
    val surname: String,
    @get:NotBlank(message = "Field must not be blank")
    @get:Email(message = "Please provide a valid email")
    val email: String,
    @get:NotBlank(message = "Field must not be blank")
    val phoneNumber: String,
    var expirationDate: LocalDate = LocalDate.now().plusYears(1),
) {
    constructor() : this("", "", "", "",)
}
