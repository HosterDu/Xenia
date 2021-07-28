package com.hosterdu.xenia

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.hosterdu.xenia.config.CustomSchemaGeneratorHooks
import graphql.schema.*
import graphql.schema.idl.SchemaParser
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*


@SpringBootApplication
class XeniaApplication{
	@Bean
	fun hooks() = CustomSchemaGeneratorHooks()
}

	fun main(args: Array<String>) {

		runApplication<XeniaApplication>(*args)
	}


