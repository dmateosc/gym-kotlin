package shared.infrastructure.bus.query

import shared.domain.bus.query.Query

interface QueryHandler<Q : Query, R : Response> {
    fun handle(query: Q): R
}