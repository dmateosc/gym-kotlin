package gym.shared.domain

import shared.domain.DomainEvent


abstract class AggregateRoot {
    private var domainEvents: MutableList<DomainEvent> = mutableListOf()
    fun pullDomainEvents(): List<DomainEvent> {
        val events: List<DomainEvent> = domainEvents
        domainEvents = mutableListOf()
        return events
    }

    protected fun record(event: DomainEvent) {
        domainEvents.add(event)
    }
}