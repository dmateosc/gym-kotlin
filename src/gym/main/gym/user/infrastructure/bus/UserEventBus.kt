package gym.user.infrastructure.bus

import gym.shared.domain.Event
import gym.shared.domain.EventBus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import shared.domain.DomainEvent

@Component
class UserEventBus(val publisher: ApplicationEventPublisher): EventBus {
    fun send(event: DomainEvent) {
        publisher.publishEvent(event)
    }

    override fun publish(event: DomainEvent) {
        publisher.publishEvent(event)
    }



}