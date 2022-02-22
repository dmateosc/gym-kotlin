package shared.infrastructure.bus.query

import org.reflections.Reflections
import shared.domain.Service
import shared.domain.bus.query.Query
import java.lang.reflect.ParameterizedType

@Service
class QueryHandlersInformation {
    var indexedQueryHandlers: HashMap<Class<out Query>, Class<out QueryHandler<Query,Response>>>


    fun search(queryClass: Class<out Query?>): Class<out QueryHandler<Query,Response>> {
        return indexedQueryHandlers[queryClass]!!

    }

    private fun formatHandlers(
        queryHandlers: Set<Class<out QueryHandler<Query,Response>>>
    ): HashMap<Class<out Query?>, Class<out QueryHandler<Query,Response>>> {
        val handlers: HashMap<Class<out Query?>, Class<out QueryHandler<Query,Response>>> =
            HashMap<Class<out Query?>, Class<out QueryHandler<Query,Response>>>()
        for (handler in queryHandlers) {
            val paramType = handler.genericInterfaces[0] as ParameterizedType
            val queryClass: Class<out Query?> = paramType.actualTypeArguments[0] as Class<out Query>
            handlers[queryClass] = handler
        }
        return handlers
    }

    init {
        val reflections = Reflections("gym")
        val classes: Set<Class<out QueryHandler<Query,Response>>> = reflections
            .getSubTypesOf(QueryHandler::class.java) as Set<Class<out QueryHandler<Query, Response>>>
        indexedQueryHandlers = formatHandlers(classes)
    }
}