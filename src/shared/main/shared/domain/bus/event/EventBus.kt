package shared.domain.bus.event

import shared.domain.DomainEvent

interface EventBus {
    fun publish(events: List<DomainEvent>)
}