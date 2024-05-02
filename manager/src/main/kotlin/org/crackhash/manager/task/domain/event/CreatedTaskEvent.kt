package org.crackhash.manager.task.domain.event

import kotlinx.serialization.Serializable

@Serializable
data class CreatedTaskEvent(
    val id: String,
    val partNumber: Int,
    val partCount: Int,
    val hash: String,
    val maxLength: Int,
    val alphabet: String
)
