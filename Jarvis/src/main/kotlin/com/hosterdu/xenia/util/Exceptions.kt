package com.hosterdu.xenia.util

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class UnauthorizedException(message: String = "This user is not authorized to do this") :
    ResponseStatusException(HttpStatus.UNAUTHORIZED, message)

class BadRequestException(message: String = "This request is incomplete") :
    ResponseStatusException(HttpStatus.BAD_REQUEST, message)

class NotFoundException(message: String = "Object not found") :
    ResponseStatusException(HttpStatus.NOT_FOUND, message)