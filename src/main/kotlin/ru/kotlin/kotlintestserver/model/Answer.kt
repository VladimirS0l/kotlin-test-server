package ru.kotlin.kotlintestserver.model

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable


data class Answer(
    @Id val id: String,
    val content: String,
): Serializable
