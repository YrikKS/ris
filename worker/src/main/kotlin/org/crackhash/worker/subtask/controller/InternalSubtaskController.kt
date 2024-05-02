package org.crackhash.worker.subtask.controller

import org.crackhash.worker.subtask.domain.SubtaskService
import org.crackhash.worker.subtask.domain.event.CreatedTaskEvent
import org.crackhash.worker.subtask.di.SubtaskRoute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(SubtaskRoute.INTERNAL_API)
class InternalSubtaskController(private val commands: SubtaskService) {

    // lab 1
    @PostMapping(SubtaskRoute.CREATE_SUBTASK)
    fun runSubtask(@RequestBody event: CreatedTaskEvent): Unit = commands.runAsync(event)

}