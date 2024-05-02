package org.crackhash.manager.task.domain.dto

data class CreateTaskRequest(
    val hash: String,
    val maxLength: Int
)
