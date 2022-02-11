package shared.infrastructure.bus.event.rabbitmq

import org.springframework.stereotype.Service
import shared.domain.DomainEvent
import shared.domain.bus.event.EventBus

@Service
class RabbitMqEventBus(val publisher: RabbitMqPublisher) : EventBus {
    private val exchangeName: String = "domain_events"
    override fun publish(events: List<DomainEvent>) {
        events.forEach(this::publish)
    }

    fun publish(domainEvent: DomainEvent){
        publisher.publish(domainEvent = domainEvent,exchangeName)
    }
}