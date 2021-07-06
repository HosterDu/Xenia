package com.example.util

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import java.util.*


object Utils {
    fun generateId(): String {
        val random = Random()
        val lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz"
        val upperCaseLetters = "abcdefghijklmnopqrstuvwxyz".uppercase(Locale.getDefault())
        val numbers = "0123456789"
        val alphabet = (lowerCaseLetters + upperCaseLetters + numbers).toCharArray()
        val size = 16
        return NanoIdUtils.randomNanoId(random, alphabet, size)

    }
}
