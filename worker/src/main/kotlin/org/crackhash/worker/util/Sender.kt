package org.crackhash.worker.util

import kotlinx.serialization.json.JsonElement

interface Sender {

    operator fun invoke(request: JsonElement)
}