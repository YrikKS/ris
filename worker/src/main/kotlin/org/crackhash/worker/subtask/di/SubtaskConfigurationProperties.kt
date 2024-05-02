package org.crackhash.worker.subtask.di

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "crack-hash-worker")
data class SubtaskConfigurationProperties(
    val version: String,
    val uri: String
)