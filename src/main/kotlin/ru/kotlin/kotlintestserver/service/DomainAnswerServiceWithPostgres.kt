package ru.kotlin.kotlintestserver.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.kotlin.kotlintestserver.model.Answer
import ru.kotlin.kotlintestserver.repository.AnswerPostgresRepository

@Service
class DomainAnswerServiceWithPostgres(
    private val answerRepository: AnswerPostgresRepository
): AnswerService {

    override fun findById(id: String): Mono<Answer?> {
        return answerRepository.findById(id)
    }

    override fun addAnswer(answer: Answer): Mono<Answer> {
        //Проблема
        answerRepository.save(answer)
        return Mono.just(answer)
    }

}

