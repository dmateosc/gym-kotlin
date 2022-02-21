package shared.domain.bus.query

import shared.domain.Service

@Service
interface QueryBus {
    fun <R> ask(query: Query): R
}