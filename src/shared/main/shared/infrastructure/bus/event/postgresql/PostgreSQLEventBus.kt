package shared.infrastructure.bus.event.postgresql

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import shared.domain.DomainEvent
import shared.domain.Service

import shared.domain.bus.event.EventBus
import shared.infrastructure.persistence.DomainEventRepository

@Primary
@Service
class PostgreSQLEventBus(@Autowired private val jpaRepository: DomainEventRepository) : EventBus {

    override fun publish(events: List<DomainEvent>) {
        events.forEach(this::publish)
    }

    private fun publish(domainEvent: DomainEvent) {
        jpaRepository.save(
            shared.infrastructure.persistence.DomainEvent(
                domainEvent.eventId!!,
                domainEvent.aggregateId!!,
                domainEvent.eventName()!!,
                domainEvent.toPrimitives(),
                domainEvent.occurredOn!!
                                                         )
                          )
    }
}