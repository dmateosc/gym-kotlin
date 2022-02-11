package shared.infrastructure.bus.event

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Primary
import shared.domain.DomainEvent
import shared.domain.bus.event.EventBus
import shared.domain.Service
import java.util.function.Consumer

@Primary
@Service
class SpringEventBus @Autowired constructor(private val applicationEventPublisher: ApplicationEventPublisher): EventBus {


    override fun publish(events: List<DomainEvent>) {
        events.forEach(Consumer { event: DomainEvent -> this.publish(event) })
    }

    private fun publish(event: DomainEvent) {
        this.applicationEventPublisher.publishEvent(event)
    }


}