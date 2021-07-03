package com.example.user.repository

import com.example.config.Database
import com.example.config.OAuth.GoogleUserProfile
import com.example.user.model.User
import com.example.user.model.UserType
import com.example.user.model.Users
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.util.*

class UserRepository {
    private fun toUser(row: ResultRow): User =
        User(
            user_id = row[Users.user_id],
            given_name = row[Users.given_name],
            family_name = row[Users.family_name],
            email = row[Users.email],
            picture = row[Users.picture],
            type = row[Users.type],
        )

    suspend fun readOrCreateAuthenticatedUser(providerProfile: GoogleUserProfile): User {
        val existingUser = retrieveUserByEmail(providerProfile)
        return if (existingUser == null) {
            createUser(providerProfile)
        } else {
            existingUser
        }

    }

    private suspend fun retrieveUserByEmail(providerProfile: GoogleUserProfile): User? {
        return Database.dbQuery {
            Users.select {
                (Users.email eq providerProfile.email)
            }.mapNotNull { toUser(it) }
                .singleOrNull()
        }
    }

    private suspend fun createUser(providerProfile: GoogleUserProfile): User {
        try {
             Database.dbQuery {
                Users.insert {
                    it[user_id] = UUID.randomUUID()
                    it[given_name] = providerProfile.given_name
                    it[family_name] = providerProfile.family_name
                    it[email] = providerProfile.email
                    it[picture] = providerProfile.picture
                    it[type] = UserType.REGISTERED
                }
            }
        } catch (e: ExposedSQLException) {
            println(e)
        }
        return retrieveUserByEmail(providerProfile)!!
    }
}