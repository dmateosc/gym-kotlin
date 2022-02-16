package shared.infrastructure.bus.event

import org.reflections.Reflections
import shared.domain.Service
import shared.infrastructure.config.DomainEventSubscriber

import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

@Service
class DomainEventSubscribersInformation {
    var information: HashMap<Class<*>, DomainEventSubscriberInformation>? = null

    constructor() {
        scanDomainEventSubscribers()
    }


    private fun scanDomainEventSubscribers(): HashMap<Class<*>, DomainEventSubscriberInformation> {
        val subscribers: Set<Class<*>> = Reflections("gym","shared")
            .getTypesAnnotatedWith(DomainEventSubscriber::class.java)
        val subscribersInformation = HashMap<Class<*>, DomainEventSubscriberInformation>()
        for (subscriberClass in subscribers) {
            val annotation: DomainEventSubscriber = subscriberClass.kotlin
                .findAnnotation()!!
            subscribersInformation[subscriberClass] =
                DomainEventSubscriberInformation(subscriberClass.kotlin, annotation.value)
        }
        this.information= subscribersInformation
        return subscribersInformation
    }

    fun all(): Collection<DomainEventSubscriberInformation> {
        return information!!.values
    }

    fun rabbitMqFormattedNames(): Array<String> {
        return information!!.values
            .map(DomainEventSubscriberInformation::formatRabbitMqQueueName)
            .distinct().toTypedArray()

    }
}