package org.crackhash.manager.util

import kotlinx.serialization.json.JsonElement
import reactor.core.publisher.Mono

interface Sender {

    operator fun invoke(requests: List<JsonElement>): Mono<Unit>
}