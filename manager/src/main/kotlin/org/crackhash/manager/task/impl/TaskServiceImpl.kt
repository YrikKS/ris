package org.crackhash.manager.task.impl

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.crackhash.manager.task.domain.TaskService
import org.crackhash.manager.task.domain.dto.CreateTaskRequest
import org.crackhash.manager.task.domain.dto.TaskResponse
import org.crackhash.manager.task.domain.dto.TaskStatus
import org.crackhash.manager.task.domain.event.CompletedSubtaskEvent
import org.crackhash.manager.task.domain.event.CreatedTaskEvent
import org.crackhash.manager.task.di.TaskConfigurationProperties
import org.crackhash.manager.task.impl.data.TaskRepository
import org.crackhash.manager.util.logger.LogBefore
import org.crackhash.manager.util.Sender
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class TaskServiceImpl(
    private val properties: TaskConfigurationProperties,
    private val repository: TaskRepository,
    private val sender: Sender
): TaskService {

    @LogBefore
    override fun createTask(request: CreateTaskRequest): Mono<String> =
        Mono.just(Task.create(request, properties))
            .flatMap { repository.add(it) }
            .map { it.id }

    @LogBefore
    override fun updateTask(event: CompletedSubtaskEvent): Mono<Unit> =
        repository.findById(event.id)
            .map { it.update(event) }
            .flatMap { repository.add(it) }
            .thenReturn(Unit)

    @LogBefore
    override fun getTaskResponse(id: String): Mono<TaskResponse> =
        repository.findById(id).map { TaskResponse(it.status, it.words) }

    @LogBefore
    @Async
    @Scheduled(fixedDelay = 30000)
    fun resendCreatedTask() {
        repository.findAll()
            .filter { it.status == TaskStatus.CREATED }
            .collectList()
            .block()?.forEach { task ->
                sender(toJsonElements(task))
                    .doOnNext { task.updateStatus(TaskStatus.IN_PROGRESS) }
                    .block()
        }
    }

    private fun toJsonElements(task: Task): List<JsonElement> =
        List(task.partCount) {
            Json.encodeToJsonElement(
                CreatedTaskEvent(task.id, it, task.partCount, task.hash, task.maxLength, task.alphabet)
            )
        }
}