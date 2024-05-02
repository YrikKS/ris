package org.crackhash.worker.subtask.config

import org.crackhash.worker.util.RabbitSender
import org.crackhash.worker.util.Sender
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableRabbit
@Configuration
@ConditionalOnProperty(prefix = "crack-hash-worker", name = ["version"], havingValue = "2")
class RabbitConfig {

    @Bean
    @ConditionalOnBean(RabbitConfig::class)
    fun sender(properties: SubtaskConfigurationProperties, template: RabbitTemplate): Sender =
        RabbitSender(template, SubtaskRoute.MANAGER_QUEUE)
}