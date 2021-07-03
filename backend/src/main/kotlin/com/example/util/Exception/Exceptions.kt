package com.example.util.Exception

class UnauthorizedException(message: String = "Denne brukeren har ikke riktig rettigheter") :
    Exception(message)