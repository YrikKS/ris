package org.crackhash.worker.subtask.port

import org.crackhash.worker.subtask.api.SubtaskService
import org.crackhash.worker.subtask.api.event.CreatedTaskEvent
import org.crackhash.worker.subtask.config.SubtaskRoute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(SubtaskRoute.INTERNAL_API)
class InternalSubtaskController(private val commands: SubtaskService) {

    @PostMapping(SubtaskRoute.CREATE_SUBTASK)
    fun runSubtask(@RequestBody event: CreatedTaskEvent): Unit = commands.runAsync(event)

}