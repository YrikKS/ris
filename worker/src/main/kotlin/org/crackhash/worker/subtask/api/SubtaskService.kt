package org.crackhash.worker.subtask.api

import org.crackhash.worker.subtask.api.event.CreatedTaskEvent
import org.springframework.scheduling.annotation.Async

interface SubtaskService {

    @Async
    fun runAsync(event: CreatedTaskEvent)

    fun run(event: CreatedTaskEvent)
}