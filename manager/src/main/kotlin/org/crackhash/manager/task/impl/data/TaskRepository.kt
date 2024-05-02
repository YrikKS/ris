package org.crackhash.manager.task.impl.data

import org.crackhash.manager.task.domain.exception.TaskNotFoundException
import org.crackhash.manager.task.impl.Task
import org.crackhash.manager.util.DomainRepository
import org.crackhash.manager.util.logger.LogAfter
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface TaskDao: ReactiveMongoRepository<Task, String>

@Repository
class TaskRepository(private val template: TaskDao): DomainRepository<Task, String> {

    override fun add(domainModel: Task): Mono<Task> =
        template.save(domainModel)

    override fun remove(domainModel: Task): Mono<Task> =
        template.deleteById(domainModel.id).thenReturn(domainModel)

    @LogAfter
    override fun findById(id: String): Mono<Task> =
        template.findById(id).onErrorMap { TaskNotFoundException(id) }

    fun findAll(): Flux<Task> =
        template.findAll()
}