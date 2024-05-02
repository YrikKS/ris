package org.crackhash.worker.util

import kotlinx.serialization.json.JsonElement
import org.springframework.amqp.rabbit.core.RabbitTemplate

class RabbitSender(
    private val template: RabbitTemplate,
    private val queue: String
) : Sender {

    override fun invoke(request: JsonElement): Unit = template.convertAndSend(queue, request.toString())
}