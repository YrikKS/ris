package org.crackhash.manager.task.controller

import org.crackhash.manager.task.domain.TaskService
import org.crackhash.manager.task.domain.dto.CreateTaskRequest
import org.crackhash.manager.task.domain.dto.TaskResponse
import org.crackhash.manager.task.domain.exception.TaskNotFoundException
import org.crackhash.manager.task.di.TaskRoute
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping(TaskRoute.API)
class TaskController(private val service: TaskService) {

    @PostMapping(TaskRoute.CREATE_TASK)
    fun createTask(@RequestBody request: CreateTaskRequest): Mono<ResponseEntity<String>> =
        service.createTask(request)
            .map { ResponseEntity(it, HttpStatus.OK) }
            .onErrorResume { Mono.just(ResponseEntity.of(handleException(it)).build()) }

    @GetMapping(TaskRoute.GET_TASK)
    fun getTask(@RequestParam(name = "requestId", required = true) id: String): Mono<ResponseEntity<TaskResponse>> =
        service.getTaskResponse(id)
            .map { ResponseEntity(it, HttpStatus.OK) }
            .onErrorResume { Mono.just(ResponseEntity.of(handleException(it)).build()) }

    private fun handleException(cause: Throwable): ProblemDetail =
        when (cause) {
            is TaskNotFoundException -> ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, cause.message)
            else -> ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, cause.message)
        }
}