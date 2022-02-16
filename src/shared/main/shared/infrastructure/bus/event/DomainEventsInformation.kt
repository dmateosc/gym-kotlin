package shared.infrastructure.bus.event



import org.springframework.stereotype.Service
import shared.domain.DomainEvent
import java.lang.reflect.InvocationTargetException
import kotlin.jvm.internal.Reflection
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

@Service
class DomainEventsInformation {

    lateinit var indexedDomainEvents: HashMap<String, KClass<out DomainEvent>>

    constructor() {

        val classes: Set<KClass<out DomainEvent>> = DomainEvent::class.java.kotlin.sealedSubclasses
            .toSet()
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

    fun forName(name: String): KClass<out DomainEvent> {
        return indexedDomainEvents[name]!!
    }

    fun forClass(domainEventClass: KClass<out DomainEvent>): String {
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
        domainEvents: Set<KClass<out DomainEvent>>
                            ): HashMap<String, KClass<out DomainEvent>> {
        val events: HashMap<String, KClass<out DomainEvent>> = HashMap()
        for (domainEvent in domainEvents) {

            events[domainEvent.java.getMethod("eventName")
                .invoke(domainEvent.java.getConstructor().newInstance()) as String] = domainEvent
        }
        return events
    }
}