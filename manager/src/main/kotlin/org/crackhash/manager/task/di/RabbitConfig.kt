package org.crackhash.manager.task.di

import org.crackhash.manager.util.RabbitSender
import org.crackhash.manager.util.Sender
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.QueueBuilder
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableRabbit
@Configuration
//@ConditionalOnProperty(prefix = "crack-hash-manager", name = ["version"], havingValue = "2")
class RabbitConfig {

    @Bean
    @ConditionalOnBean(RabbitConfig::class)
    fun declarables(properties: TaskConfigurationProperties): Declarables =
        Declarables(
            QueueBuilder.durable(TaskRoute.MANAGER_QUEUE)
                .deadLetterExchange(TaskRoute.MANAGER_QUEUE)
                .ttl(properties.ttl.toMillis().toInt())
                .build(),
            QueueBuilder.durable(TaskRoute.WORKER_QUEUE)
                .deadLetterExchange(TaskRoute.WORKER_QUEUE)
                .ttl(properties.ttl.toMillis().toInt())
                .build()
        )

    @Bean
    @ConditionalOnBean(RabbitConfig::class)
    fun sender(properties: TaskConfigurationProperties, template: RabbitTemplate): Sender =
        RabbitSender(template, TaskRoute.WORKER_QUEUE)
}