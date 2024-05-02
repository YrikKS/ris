package org.crackhash.manager.task.impl.data

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.crackhash.manager.task.di.TaskConfigurationProperties
import org.crackhash.manager.task.impl.Task
import org.crackhash.manager.util.DomainCacheRepository
import org.crackhash.manager.util.logger.LogAfter
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import reactor.core.publisher.Mono

//@Repository
class TaskCacheRepository(
    private val properties: TaskConfigurationProperties,
    private val template: ReactiveStringRedisTemplate,
    private val repository: TaskRepository
): DomainCacheRepository<Task, String>(repository) {

    override fun addInCache(domainModel: Task): Mono<Unit> =
        template.opsForValue()
            .set(domainModel.id, Json.encodeToString(domainModel), properties.ttl)
            .thenReturn(Unit)

    override fun deleteFromCache(domainModel: Task): Mono<Unit> =
        template.opsForValue().delete(domainModel.id).thenReturn(Unit)

    @LogAfter
    override fun findInCache(id: String): Mono<Task> =
        template.opsForValue().get(id).map { Json.decodeFromString<Task>(it) }
}