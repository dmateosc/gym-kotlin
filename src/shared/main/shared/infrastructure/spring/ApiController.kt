package shared.infrastructure.spring

import shared.domain.bus.command.Command
import shared.domain.bus.command.CommandBus
import shared.domain.bus.query.Query
import shared.domain.bus.query.QueryBus

open class ApiController(private var queryBus: QueryBus, private var commandBus: CommandBus) {

    protected open fun dispatch(command: Command) {
        commandBus.dispatch(command)
    }

    protected open fun ask(query: Query): shared.infrastructure.bus.query.Response {
        return queryBus.ask(query)
    }

}