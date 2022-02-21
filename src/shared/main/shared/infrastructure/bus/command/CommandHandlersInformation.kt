package shared.infrastructure.bus.command

import org.reflections.Reflections
import shared.domain.Service
import shared.domain.bus.command.Command
import shared.domain.bus.command.CommandHandler
import java.lang.reflect.ParameterizedType

@Service
class CommandHandlersInformation() {

    private  var indexedCommandHandlers: HashMap<Class<out Command>, Class<out CommandHandler<*>>>

    init {
        val reflections = Reflections("gym")
        val classes: Set<Class<out CommandHandler<*>>> = setOf(CommandHandler::class.java)

        indexedCommandHandlers = formatHandlers(classes)
    }

    fun search(commandClass: Class<out Command?>): Class<out CommandHandler<*>> {
        return indexedCommandHandlers[commandClass]
            ?: throw Exception()
    }

    private fun formatHandlers(
        commandHandlers: Set<Class<out CommandHandler<*>>>
                              ): java.util.HashMap<Class<out Command>, Class<out CommandHandler<*>>> {
        val handlers: HashMap<Class<out Command>, Class<out CommandHandler<*>>> =
            HashMap()
        for (handler in commandHandlers) {
            val paramType = handler.genericInterfaces as ParameterizedType
            val commandClass = paramType.actualTypeArguments[0] as Class<out Command>
            handlers[commandClass] = handler
        }
        return handlers
    }


}