package shared.infrastructure.bus.event.rabbitmq

import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessagePropertiesBuilder
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import shared.domain.DomainEvent
import shared.infrastructure.bus.event.DomainEventJsonSerializer


@Service
class RabbitMqPublisher(private val rabbitTemplate: RabbitTemplate) {

    fun publish(domainEvent: DomainEvent, exchangeName: String){

        val serializedDomainEvent = DomainEventJsonSerializer().serialize(domainEvent)

        val message = Message(
            serializedDomainEvent.encodeToByteArray(),
            MessagePropertiesBuilder.newInstance().setContentEncoding("utf-8").setContentType("application/json").build()
                         )
        rabbitTemplate.send(exchangeName, domainEvent.eventName()!!,message)

    }

    fun publish(domainEvent: Message, exchangeName: String, routingKey: String){
        rabbitTemplate.send(exchangeName,routingKey, domainEvent)
    }

}