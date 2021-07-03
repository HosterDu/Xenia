package com.example.user.model

import com.example.util.Serializable.UUIDSerializer
import io.ktor.auth.Principal
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import java.util.*

enum class UserType{
    REGISTERED,
    GUEST
}

object Users : Table() {
    val id = uuid("id")
    val given_name = varchar("given_name", 64)
    val family_name = varchar("family_name", 64)
    val email = varchar("email", 64).uniqueIndex()
    val picture = varchar("picture", 128)
    val type = enumerationByName("type", 32,UserType::class).default(UserType.GUEST)
    override val primaryKey = PrimaryKey(id, name = "PK_User_Id")
}

@Serializable
data class User(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val given_name: String?,
    val family_name: String?,
    val email: String?,
    val picture: String?,
    val type: UserType = UserType.GUEST
) : Principal {
    override fun toString(): String {
        return "User(id=$id, given_name=$given_name, family_name=$family_name, email=$email, picture=$picture, type=$type)"
    }
}