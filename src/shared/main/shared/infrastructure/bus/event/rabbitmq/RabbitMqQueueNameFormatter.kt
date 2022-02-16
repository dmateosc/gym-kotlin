package shared.infrastructure.bus.event.rabbitmq

import shared.infrastructure.bus.event.DomainEventSubscriberInformation

object RabbitMqQueueNameFormatter {
    fun format(information: DomainEventSubscriberInformation): String {
        return information.formatRabbitMqQueueName()
    }

    fun formatRetry(information: DomainEventSubscriberInformation): String {
        return String.format("retry.%s", format(information))
    }

    fun formatDeadLetter(information: DomainEventSubscriberInformation): String {
        return String.format("dead_letter.%s", format(information))
    }
}