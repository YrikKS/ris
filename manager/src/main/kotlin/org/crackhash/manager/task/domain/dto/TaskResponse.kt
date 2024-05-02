package org.crackhash.manager.task.domain.dto

data class TaskResponse(
    val status: TaskStatus,
    val words: Set<String>
)
