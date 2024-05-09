package org.crackhash.worker.subtask.impl

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import org.apache.commons.codec.digest.DigestUtils
import org.crackhash.worker.subtask.domain.SubtaskService
import org.crackhash.worker.subtask.domain.event.CompletedSubtaskEvent
import org.crackhash.worker.subtask.domain.event.CreatedTaskEvent
import org.crackhash.worker.util.Sender
import org.crackhash.worker.util.logger.LogBefore
import org.paukov.combinatorics3.Generator
import org.springframework.stereotype.Service
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
        (1..event.maxLength)
            .asSequence()
            .flatMap {
                Generator.permutation(event.alphabet.split(""))
                    .withRepetitions(event.maxLength)
                    .stream()
                    .asSequence()
            }
            .drop(getSkipElementsNumber(event))
            .take(getTakeElementsCount(event))
            .map { it.joinToString("") }
            .filter { DigestUtils.md5Hex(it) == event.hash }
            .toSet()

    private fun getSkipElementsNumber(event: CreatedTaskEvent): Int =
        event.partNumber * (getWordsCount(event.maxLength, event.alphabet.length).div(event.partCount) - 1)

    private fun getTakeElementsCount(event: CreatedTaskEvent): Int =
        getWordsCount(event.maxLength, event.alphabet.length).div(event.partCount) + 1

    private infix fun Int.combinationWithRep(alphabet: Int): Int {
        return (1..this).fold(1) { acc, i -> acc * alphabet }
    }

    private fun getWordsCount(wordsMaxLength: Int, alphabetSize: Int): Int {
        return (1..wordsMaxLength)
            .map { it combinationWithRep alphabetSize }
            .reduce(Int::plus)
    }

}