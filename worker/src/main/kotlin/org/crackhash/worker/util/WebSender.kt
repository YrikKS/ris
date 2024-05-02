package org.crackhash.worker.util

import kotlinx.serialization.json.JsonElement
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

// Lab 1
class WebSender(private val requestBodySpec: RestClient.RequestBodySpec) : Sender {

    override fun invoke(request: JsonElement): Unit =
        checkNotNull(requestBodySpec.body(request).retrieve().body()) { "Rest client sending error" }
}