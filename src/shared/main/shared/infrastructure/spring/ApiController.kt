package shared.infrastructure.spring

import shared.domain.bus.command.Command
import shared.domain.bus.command.CommandBus
import shared.domain.bus.query.Query
import shared.domain.bus.query.QueryBus

open class ApiController(private var queryBus: QueryBus, private var commandBus: CommandBus) {

    protected open fun dispatch(command: Command) {
        commandBus.dispatch(command)
    }

    protected open fun <R> ask(query: Query): R {
        return queryBus.ask(query)
    }

}