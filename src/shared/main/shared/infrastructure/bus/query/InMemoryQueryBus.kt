package shared.infrastructure.bus.query

import org.springframework.context.ApplicationContext
import shared.domain.Service
import shared.domain.bus.query.Query
import shared.domain.bus.query.QueryBus


@Service
class InMemoryQueryBus(information: QueryHandlersInformation, context: ApplicationContext) :
    QueryBus {
    private val information: QueryHandlersInformation
    private val context: ApplicationContext
    override fun ask(query: Query): Response {

            val queryHandlerClass: Class<out QueryHandler<Query, shared.infrastructure.bus.query.Response>> = information.search(query::class.java)
            val handler: QueryHandler<Query, shared.infrastructure.bus.query.Response> = context.getBean(queryHandlerClass)
        return   handler.handle(query)
    }

    init {
        this.information = information
        this.context = context
    }
}
