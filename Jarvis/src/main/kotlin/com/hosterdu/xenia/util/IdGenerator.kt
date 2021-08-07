package com.hosterdu.xenia.util

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import org.hibernate.HibernateException
import org.hibernate.engine.spi.SessionImplementor
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import java.io.Serializable
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*


class IdGenerator : IdentifierGenerator {

    override fun generate(session: SharedSessionContractImplementor?, `object`: Any?): Serializable {
        val random = Random()
        val lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz"
        val upperCaseLetters = lowerCaseLetters.uppercase(Locale.getDefault())
        val numbers = "0123456789"
        val alphabet =  (lowerCaseLetters + upperCaseLetters + numbers).toCharArray()
        val size = 16
        return NanoIdUtils.randomNanoId(random, alphabet, size)
    }
}