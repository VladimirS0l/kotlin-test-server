package ru.kotlin.kotlintestserver.service

import reactor.core.publisher.Mono
import ru.kotlin.kotlintestserver.model.Answer

interface AnswerService {
    fun findById(id: String): Mono<Answer?>
    fun addAnswer(answer: Answer): Mono<Answer>
}