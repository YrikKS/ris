package org.crackhash.manager.task.port

import com.rabbitmq.client.Channel
import kotlinx.serialization.json.Json
import org.crackhash.manager.task.api.TaskService
import org.crackhash.manager.task.config.RabbitConfig
import org.crackhash.manager.task.config.TaskRoute
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.AmqpHeaders
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
@ConditionalOnBean(RabbitConfig::class)
class TaskConsumer(private val service: TaskService) {

    @RabbitListener(queues = [TaskRoute.MANAGER_QUEUE])
    fun consume(request: String, channel: Channel, @Header(AmqpHeaders.DELIVERY_TAG) tag: Long): Unit =
        checkNotNull(
            service.updateTask(Json.decodeFromString(request))
                .doOnSuccess { channel.basicAck(tag, false) }
                .block()
        ) { "Update task error" }
}