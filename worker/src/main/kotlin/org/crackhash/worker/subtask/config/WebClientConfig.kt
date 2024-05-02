package org.crackhash.worker.subtask.config

import org.crackhash.worker.util.WebSender
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.web.client.RestClient

@EnableAsync
@Configuration
class WebClientConfig {

    @Bean
    @ConditionalOnMissingBean(RabbitConfig::class)
    fun sender(properties: SubtaskConfigurationProperties): WebSender =
        WebSender(
            RestClient.create(properties.uri)
                .method(HttpMethod.PATCH)
                .uri(SubtaskRoute.INTERNAL_API + SubtaskRoute.UPDATE_TASK)
        )
}