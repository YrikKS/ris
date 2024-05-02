package org.crackhash.worker.subtask.controller

import com.rabbitmq.client.Channel
import kotlinx.serialization.json.Json
import org.crackhash.worker.subtask.domain.SubtaskService
import org.crackhash.worker.subtask.domain.event.CreatedTaskEvent
import org.crackhash.worker.subtask.di.SubtaskRoute
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.AmqpHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class SubtaskConsumer(private val commands: SubtaskService) {

    @RabbitListener(queues = [SubtaskRoute.WORKER_QUEUE])
    fun consume(request: String, channel: Channel, @Header(AmqpHeaders.DELIVERY_TAG) tag: Long): Unit =
        run { commands.run(Json.decodeFromString<CreatedTaskEvent>(request)) }
            .also { channel.basicAck(tag, false) }
}