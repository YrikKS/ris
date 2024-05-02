package org.crackhash.manager.task.api.dto

data class TaskResponse(
    val status: TaskStatus,
    val words: Set<String>
)
