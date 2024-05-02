package org.crackhash.manager.task.config

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration
import kotlin.time.toKotlinDuration

@ConfigurationProperties(prefix = "crack-hash-manager")
data class TaskConfigurationProperties(
    val version: Int,
    val ttl: Duration,
    val partCount: Int,
    val alphabet: String,
    val uri: String
) {

    init {
        require(version in 1..2) { "Version=${version} must be 1 or 2" }
        require(ttl.isPositive) { "Ttl=${ttl.toKotlinDuration()} must be positive" }
        require(partCount > 0) { "Part count=${partCount} must be more positive" }
        require(alphabet.isNotEmpty()) { "Alphabet=${alphabet} must be not empty" }
        require(alphabet.all(hashSetOf<Char>()::add)) { "Chars in alphabet=${alphabet} must be unique" }
        require(uri.isNotEmpty()) { "Uri=${uri} must be not empty" }
    }
}
