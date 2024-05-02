package org.crackhash.manager.task.api

import org.crackhash.manager.task.api.event.CompletedSubtaskEvent
import org.crackhash.manager.task.api.dto.CreateTaskRequest
import org.crackhash.manager.task.api.dto.TaskResponse
import reactor.core.publisher.Mono

interface TaskService {

    fun createTask(request: CreateTaskRequest): Mono<String>

    fun updateTask(event: CompletedSubtaskEvent): Mono<Unit>

    fun getTaskResponse(id: String): Mono<TaskResponse>
}