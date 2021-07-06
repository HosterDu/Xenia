package com.example.event.model

import com.example.user.model.Users
import com.example.util.Serializable.DateTimeSerializer
import com.example.util.Serializable.UUIDSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime
import java.util.*


object Events : Table() {
    val id = varchar("id", 32)
    val title = varchar("title", 128)
    val description = text("description")
    val start_date_time = datetime("start_date_time")
    val location = varchar("location", 128)
    val picture = varchar("picture", 128)
    val createdById = uuid("createdById") references Users.id
    override val primaryKey = PrimaryKey(id, name = "PK_Event_Id")
}

@Serializable
data class Event(
    val id: String? = null,
    val title: String,
    val description: String? = null,
    @Serializable(with = DateTimeSerializer::class)
    val start_date_time: LocalDateTime,
    val location: String? = null,
    val picture: String?,
    @Serializable(with = UUIDSerializer::class)
    val createdById: UUID? = null
)
