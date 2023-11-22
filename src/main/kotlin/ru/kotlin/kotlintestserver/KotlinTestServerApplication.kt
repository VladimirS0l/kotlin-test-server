package ru.kotlin.kotlintestserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinTestServerApplication

fun main(args: Array<String>) {
	runApplication<KotlinTestServerApplication>(*args)
}
