package shared.domain

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

abstract class DomainEvent {
    var aggregateId: String? = null
    var eventId: String? = null
    var occurredOn: String? = null

    fun DomainEvent(aggregateId: String?) {
        this.aggregateId = aggregateId
        eventId = UUID.randomUUID().toString()
        occurredOn = LocalDateTime.now().toString()
    }

    fun DomainEvent(aggregateId: String?, eventId: String?, occurredOn: String?) {
        this.aggregateId = aggregateId
        this.eventId = eventId
        this.occurredOn = occurredOn
    }

    protected fun DomainEvent() {}

    abstract fun eventName(): String?

    abstract fun toPrimitives(): HashMap<String, Serializable>

    abstract fun fromPrimitives(
        aggregateId: String?,
        body: HashMap<String?, Serializable?>?,
        eventId: String?,
        occurredOn: String?
                               ): DomainEvent?

    fun aggregateId(): String? {
        return aggregateId
    }

    fun eventId(): String? {
        return eventId
    }

    fun occurredOn(): String? {
        return occurredOn
    }
}