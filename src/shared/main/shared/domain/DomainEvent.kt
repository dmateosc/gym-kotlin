package shared.domain

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.HashMap

abstract class DomainEvent() {
    var aggregateId: String? = null
    var eventId: String? = null
    var occurredOn: String? = null

    constructor(aggregateId: String?):this() {
        this.aggregateId = aggregateId
        eventId = UUID.randomUUID().toString()
        occurredOn = LocalDateTime.now().toString()
    }

    constructor(aggregateId: String?, eventId: String?, occurredOn: String?) :this(){
        this.aggregateId = aggregateId
        this.eventId = eventId
        this.occurredOn = occurredOn
    }

    abstract fun eventName(): String?

    abstract fun toPrimitives(): HashMap<String, Serializable>

    abstract fun fromPrimitives(
        aggregateId: String,
        body: HashMap<String, Serializable>,
        eventId: String,
        occurredOn: String
                               ): DomainEvent

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