package gym.user.infrastructure.bus

import gym.shared.domain.Event
import gym.shared.domain.EventBus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class UserEventBus(val publisher: ApplicationEventPublisher): EventBus {
    override fun send(event: Event) {
        publisher.publishEvent(event)
    }


}