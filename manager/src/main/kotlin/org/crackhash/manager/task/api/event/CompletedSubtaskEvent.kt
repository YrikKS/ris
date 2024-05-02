package org.crackhash.manager.task.api.event

import kotlinx.serialization.Serializable

@Serializable
data class CompletedSubtaskEvent(
    val id: String,
    val partNumber: Int,
    val words: Set<String>
)