package shared.infrastructure.bus.event

import shared.domain.DomainEvent
import shared.domain.Utils
import kotlin.reflect.KClass

class DomainEventSubscriberInformation {
    private var subscriberClass: KClass<*>
    private var subscribedEvents: Array<KClass<out DomainEvent>>

    constructor(
        subscriberClass: KClass<*>,
        subscribedEvents: Array<KClass<out DomainEvent>>
                                        ) {
        this.subscriberClass = subscriberClass
        this.subscribedEvents = subscribedEvents
    }

    fun subscriberClass(): KClass<*> {
        return subscriberClass
    }

    fun contextName(): String {
        return subscriberClass.simpleName!!
    }

    fun moduleName(): String {
                val nameParts = subscriberClass.qualifiedName!!.split("\\.".toRegex()).toTypedArray()
        return nameParts[3]
    }

    fun className(): String {
        return subscriberClass.simpleName!!
    }

    fun subscribedEvents(): Array<KClass<out DomainEvent>> {
        return subscribedEvents
    }

    fun formatRabbitMqQueueName(): String {
        return java.lang.String.format("gym.%s.%s.%s", contextName(), moduleName(), Utils.toSnake(className()))
    }
}
