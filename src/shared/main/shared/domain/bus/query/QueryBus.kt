package shared.domain.bus.query

import shared.infrastructure.bus.query.Response


interface QueryBus {
    fun ask(query: Query): Response
}
