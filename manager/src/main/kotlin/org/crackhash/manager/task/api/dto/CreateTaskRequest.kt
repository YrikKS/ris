package org.crackhash.manager.task.api.dto

data class CreateTaskRequest(
    val hash: String,
    val maxLength: Int
)
