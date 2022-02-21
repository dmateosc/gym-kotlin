package shared.infrastructure.bus.command


import org.springframework.context.ApplicationContext
import shared.domain.Service
import shared.domain.bus.command.Command
import shared.domain.bus.command.CommandBus
import shared.domain.bus.command.CommandHandler

@Service
class InMemoryCommandBus(private val information: CommandHandlersInformation, val context: ApplicationContext) : CommandBus {
    override fun dispatch(command: Command) {
        val commandHandlerClass: Class<out CommandHandler<Command>> =
            information.search(command::class.java) as Class<out CommandHandler<Command>>

        val handler = context.getBean(commandHandlerClass)

        handler.handle(command = command)
    }
}