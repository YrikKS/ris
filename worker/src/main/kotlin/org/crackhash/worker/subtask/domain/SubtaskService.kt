package org.crackhash.worker.subtask.domain

import org.crackhash.worker.subtask.domain.event.CreatedTaskEvent
import org.springframework.scheduling.annotation.Async

interface SubtaskService {

    @Async
    fun runAsync(event: CreatedTaskEvent)

    fun run(event: CreatedTaskEvent)
}