package org.crackhash.manager.task.domain

import org.crackhash.manager.task.domain.event.CompletedSubtaskEvent
import org.crackhash.manager.task.domain.dto.CreateTaskRequest
import org.crackhash.manager.task.domain.dto.TaskResponse
import reactor.core.publisher.Mono

interface TaskService {

    fun createTask(request: CreateTaskRequest): Mono<String>

    fun updateTask(event: CompletedSubtaskEvent): Mono<Unit>

    fun getTaskResponse(id: String): Mono<TaskResponse>
}