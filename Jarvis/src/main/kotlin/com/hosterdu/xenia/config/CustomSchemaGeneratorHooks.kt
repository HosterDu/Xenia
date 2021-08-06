package com.hosterdu.xenia.config

import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.GraphQLScalarType
import graphql.schema.GraphQLType
import java.time.ZonedDateTime
import kotlin.reflect.KClass
import kotlin.reflect.KType

class CustomSchemaGeneratorHooks : SchemaGeneratorHooks {

    override fun willGenerateGraphQLType(type: KType): GraphQLType? = when (type.classifier as? KClass<*>) {
        ZonedDateTime::class -> graphqlZonedDateTimeType
        Long::class -> graphqlLongTimeType
        else -> null
    }
}

val graphqlZonedDateTimeType = GraphQLScalarType.newScalar()
    .name("ZonedDateTime")
    .description("A type representing a formatted java.time.ZonedDateTime")
    .coercing(ZonedDateTimeCoercing)
    .build()

object ZonedDateTimeCoercing : Coercing<ZonedDateTime, String> {
    override fun parseValue(input: Any?): ZonedDateTime = ZonedDateTime.parse(serialize(input))

    override fun parseLiteral(input: Any?): ZonedDateTime? {
        val timeString = (input as? StringValue)?.value
        return ZonedDateTime.parse(timeString)
    }

    override fun serialize(dataFetcherResult: Any?): String = dataFetcherResult.toString()
}

val graphqlLongTimeType = GraphQLScalarType.newScalar()
    .name("Long")
    .description("A type representing a Kotlin Long")
    .coercing(LongCoercing)
    .build()

object LongCoercing : Coercing<Long, String> {
    override fun parseValue(input: Any?): Long = (serialize(input)).toLong()

    override fun parseLiteral(input: Any?): Long? {
        val timeString = (input as? StringValue)?.value
        return timeString?.toLong()
    }

    override fun serialize(dataFetcherResult: Any?): String = dataFetcherResult.toString()
}