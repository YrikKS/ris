package org.crackhash.worker.subtask.impl

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.apache.commons.codec.digest.DigestUtils
import org.crackhash.worker.subtask.domain.SubtaskService
import org.crackhash.worker.subtask.domain.event.CompletedSubtaskEvent
import org.crackhash.worker.subtask.domain.event.CreatedTaskEvent
import org.crackhash.worker.util.logger.LogBefore
import org.crackhash.worker.util.Sender
import org.paukov.combinatorics3.Generator
import org.springframework.stereotype.Service
import kotlin.math.pow
import kotlin.streams.asSequence

@Service
class SubtaskServiceImpl(private val sender: Sender) : SubtaskService {

    // Lab 1
    @LogBefore
    override fun runAsync(event: CreatedTaskEvent): Unit = run(event)

    @LogBefore
    override fun run(event: CreatedTaskEvent): Unit =
        sender(
            Json.encodeToJsonElement(
                CompletedSubtaskEvent(event.id, event.partNumber, findWords(event))
            )
        )

    private fun findWords(event: CreatedTaskEvent): Set<String> =
        Generator.permutation(event.alphabet.split(""))
            .withRepetitions(event.maxLength)
            .stream()
            .asSequence()
            .drop(getSkipElementsNumber(event).toInt())
//            .take(event.partNumber)
            .map { it.joinToString("") }
            .filter { DigestUtils.md5Hex(it) == event.hash }
//            .toList()
            .toSet()

    private fun getSkipElementsNumber(event: CreatedTaskEvent): Long =
        event.partNumber * (event.alphabet.chars().count().toDouble().pow(event.maxLength)).toLong()

}