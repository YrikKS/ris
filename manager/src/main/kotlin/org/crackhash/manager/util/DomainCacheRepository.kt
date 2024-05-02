package org.crackhash.manager.util

import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.publisher.switchIfEmpty

abstract class DomainCacheRepository<V, ID>(private val repository: DomainRepository<V, ID>): DomainRepository<V, ID> {

    abstract fun addInCache(domainModel: V): Mono<Unit>

    abstract fun deleteFromCache(domainModel: V): Mono<Unit>

    abstract fun findInCache(id: ID): Mono<V>

    override fun add(domainModel: V): Mono<V> =
        Mono.zip(repository.add(domainModel), addInCache(domainModel)).map { it.t1 }

    override fun remove(domainModel: V): Mono<V> =
        Mono.zip(repository.remove(domainModel), deleteFromCache(domainModel)).map { it.t1 }

    override fun findById(id: ID): Mono<V> =
        findInCache(id).switchIfEmpty {
            repository.findById(id)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext { addInCache(it).subscribe() }
        }
}