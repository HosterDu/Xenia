package com.hosterdu.xenia

import com.hosterdu.xenia.config.CustomSchemaGeneratorHooks
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class XeniaApplication{
	@Bean
	fun hooks() = CustomSchemaGeneratorHooks()
}

	fun main(args: Array<String>) {

		runApplication<XeniaApplication>(*args)
	}


