package gym.shared.domain

import shared.domain.DomainEvent

interface EventBus {
    fun publish(event: DomainEvent)
}