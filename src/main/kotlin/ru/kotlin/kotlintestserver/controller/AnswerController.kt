package ru.kotlin.kotlintestserver.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.kotlin.kotlintestserver.model.Answer
import ru.kotlin.kotlintestserver.service.AnswerService

@RestController
@RequestMapping("answer/qiwi")
class AnswerController(
    private val answerService: AnswerService
) {
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: String) = answerService.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addAnswer(@RequestBody answer: Answer) = answerService.addAnswer(answer)
}