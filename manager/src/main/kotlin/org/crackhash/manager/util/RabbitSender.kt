package org.crackhash.manager.util

import kotlinx.serialization.json.JsonElement
import org.springframework.amqp.rabbit.core.RabbitTemplate
import reactor.core.publisher.Mono

class RabbitSender(
    private val template: RabbitTemplate,
    private val queue: String
) : Sender {

    override fun invoke(requests: List<JsonElement>): Mono<Unit> =
        Mono.just(requests.forEach { template.convertAndSend(queue, it.toString()) })
}