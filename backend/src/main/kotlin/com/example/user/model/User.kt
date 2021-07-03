package com.example.user.model

import io.ktor.auth.Principal
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.util.*

enum class UserType{
    REGISTERED,
    GUEST
}

object Users : Table() {
    val user_id: Column<UUID> = uuid("user_id")
    val given_name: Column<String> = varchar("given_name", 45)
    val family_name: Column<String> = varchar("family_name", 45)
    val email: Column<String> = varchar("email", 45).uniqueIndex()
    val picture: Column<String> = varchar("picture", 128)
    val type: Column<UserType> = enumerationByName("type", 45,UserType::class).default(UserType.GUEST)
    override val primaryKey = PrimaryKey(user_id, name = "PK_User_Id")
}

data class User(
    val user_id: UUID,
    val given_name: String?,
    val family_name: String?,
    val email: String?,
    val picture: String?,
    val type: UserType = UserType.GUEST
) : Principal {
    override fun toString(): String {
        return "User(user_id=$user_id, given_name=$given_name, family_name=$family_name, email=$email, picture=$picture, type=$type)"
    }
}