package org.crackhash.manager.util

import reactor.core.publisher.Mono

interface DomainRepository<V, ID> {

    fun add(domainModel: V): Mono<V>

    fun remove(domainModel: V): Mono<V>

    fun findById(id: ID): Mono<V>
}