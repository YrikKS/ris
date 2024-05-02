package org.crackhash.manager.task.config

import org.crackhash.manager.util.WebSender
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    @ConditionalOnMissingBean(RabbitConfig::class)
    fun sender(properties: TaskConfigurationProperties): WebSender =
        WebSender(
            WebClient.create(properties.uri)
                .method(HttpMethod.POST)
                .uri(TaskRoute.INTERNAL_API + TaskRoute.CREATE_SUBTASK)
        )
}