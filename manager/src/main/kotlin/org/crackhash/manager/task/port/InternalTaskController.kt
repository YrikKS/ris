package org.crackhash.manager.task.port

import org.crackhash.manager.task.api.TaskService
import org.crackhash.manager.task.api.event.CompletedSubtaskEvent
import org.crackhash.manager.task.config.RabbitConfig
import org.crackhash.manager.task.config.TaskRoute
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@ConditionalOnMissingBean(RabbitConfig::class)
@RequestMapping(TaskRoute.INTERNAL_API + TaskRoute.UPDATE_TASK)
class InternalTaskController(private val service: TaskService) {

    @PatchMapping
    fun updateTask(@RequestBody event: CompletedSubtaskEvent): Mono<Unit> = service.updateTask(event)
}