package ru.kotlin.kotlintestserver.repository

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import ru.kotlin.kotlintestserver.model.Answer


interface AnswerPostgresRepository: ReactiveCrudRepository<Answer, String> {
}