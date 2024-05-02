package org.crackhash.manager.util

import kotlinx.serialization.json.JsonElement
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class WebSender(private val requestBodySpec: WebClient.RequestBodySpec) : Sender {

    override fun invoke(requests: List<JsonElement>): Mono<Unit> =
        Flux.fromIterable(List(requests.size) {
            requestBodySpec.bodyValue(requests[it])
                .retrieve()
                .bodyToMono(Unit::class.java)
        })
            .flatMap { it }
            .collectList()
            .thenReturn(Unit)
}