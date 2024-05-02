package org.crackhash.manager.task.controller

import org.crackhash.manager.task.domain.TaskService
import org.crackhash.manager.task.domain.event.CompletedSubtaskEvent
import org.crackhash.manager.task.di.RabbitConfig
import org.crackhash.manager.task.di.TaskRoute
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

// Lab 1
@RestController
@ConditionalOnMissingBean(RabbitConfig::class)
@RequestMapping(TaskRoute.INTERNAL_API + TaskRoute.UPDATE_TASK)
class InternalTaskController(private val service: TaskService) {

    @PatchMapping
    fun updateTask(@RequestBody event: CompletedSubtaskEvent): Mono<Unit> = service.updateTask(event)
}