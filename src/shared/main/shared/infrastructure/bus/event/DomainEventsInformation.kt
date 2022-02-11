package shared.infrastructure.bus.event

import org.reflections.Reflections
import org.springframework.stereotype.Service
import shared.domain.DomainEvent
import java.lang.reflect.InvocationTargetException
import java.util.function.Function

@Service
class DomainEventsInformation {

    lateinit var indexedDomainEvents: HashMap<String, Class<out DomainEvent>>

    fun DomainEventsInformation() {
        val reflections = Reflections("gym")
        val classes: Set<Class<out DomainEvent>> = reflections.getSubTypesOf(DomainEvent::class.java)
        try {
            indexedDomainEvents = formatEvents(classes)
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }

    fun forName(name: String): Class<out DomainEvent> {
        return indexedDomainEvents[name]!!
    }

    fun forClass(domainEventClass: Class<out DomainEvent>): String {
        return indexedDomainEvents.entries
            .filter { (_, value) ->
                value == domainEventClass
            }
            .map { (key, value) -> key }
            .first()
    }

    @Throws(
        NoSuchMethodException::class,
        IllegalAccessException::class,
        InvocationTargetException::class,
        InstantiationException::class
           )
    private fun formatEvents(
        domainEvents: Set<Class<out DomainEvent>>
                            ): HashMap<String, Class<out DomainEvent>> {
        val events: HashMap<String, Class<out DomainEvent>> = HashMap()
        for (domainEvent in domainEvents) {
            val nullInstance: DomainEvent = domainEvent.getConstructor().newInstance()
            events[domainEvent.getMethod("eventName").invoke(nullInstance) as String] = domainEvent
        }
        return events
    }
}