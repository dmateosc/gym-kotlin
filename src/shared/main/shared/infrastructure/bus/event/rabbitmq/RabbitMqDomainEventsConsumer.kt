package shared.infrastructure.bus.event.rabbitmq

import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageBuilder
import org.springframework.amqp.core.MessagePropertiesBuilder
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry
import org.springframework.context.ApplicationContext
import shared.domain.DomainEvent
import shared.domain.Service
import shared.domain.Utils
import shared.infrastructure.bus.event.DomainEventJsonDeserializer
import shared.infrastructure.bus.event.DomainEventSubscribersInformation
import java.lang.reflect.InvocationTargetException

@Service
class RabbitMqDomainEventsConsumer {

    private val MAX_RETRIES = 2
    private var deserializer: DomainEventJsonDeserializer
    private var context: ApplicationContext? = null
    private var publisher: RabbitMqPublisher? = null
    private val domainEventSubscribers = HashMap<String, Any>()
    var registry: RabbitListenerEndpointRegistry
    private var information: DomainEventSubscribersInformation? = null

    constructor(
        information: DomainEventSubscribersInformation,
        deserializer: DomainEventJsonDeserializer,
        context: ApplicationContext?,
        registry: RabbitListenerEndpointRegistry,
        publisher: RabbitMqPublisher?
                                    ) {
        this.registry = registry
        this.information = information
        this.deserializer = deserializer
        this.context = context
        this.publisher = publisher
    }

    fun consume() {
        val container = registry!!.getListenerContainer(
            Companion.CONSUMER_NAME
                                                       ) as AbstractMessageListenerContainer
        container.addQueueNames(*information!!.rabbitMqFormattedNames())
        container.start()
    }

    @RabbitListener(id = Companion.CONSUMER_NAME, autoStartup = "false")
    @Throws(Exception::class)
    fun consumer(message: Message) {
        val serializedMessage = String(message.body)
        val domainEvent: DomainEvent = deserializer!!.deserialize(serializedMessage)!!
        val queue = message.messageProperties.consumerQueue
        val subscriber =
            if (domainEventSubscribers.containsKey(queue)) domainEventSubscribers[queue] else subscriberFor(queue)
        val subscriberOnMethod = subscriber!!::class.java.getMethod("on", domainEvent::class.java)
        try {
            subscriberOnMethod.invoke(subscriber, domainEvent)
        } catch (error: IllegalAccessException) {
            throw Exception(
                java.lang.String.format(
                    "The subscriber <%s> should implement a method `on` listening the domain event <%s>",
                    queue,
                    domainEvent.eventName()
                                       )
                           )
        } catch (error: IllegalArgumentException) {
            throw Exception(
                java.lang.String.format(
                    "The subscriber <%s> should implement a method `on` listening the domain event <%s>",
                    queue,
                    domainEvent.eventName()
                                       )
                           )
        } catch (error: InvocationTargetException) {
            throw Exception(
                java.lang.String.format(
                    "The subscriber <%s> should implement a method `on` listening the domain event <%s>",
                    queue,
                    domainEvent.eventName()
                                       )
                           )
        } catch (error: Exception) {
            handleConsumptionError(message, queue)
        }
    }

    fun withSubscribersInformation(information: DomainEventSubscribersInformation?) {
        this.information = information
    }

    private fun handleConsumptionError(message: Message, queue: String) {
        if (hasBeenRedeliveredTooMuch(message)) {
            sendToDeadLetter(message, queue)
        } else {
            sendToRetry(message, queue)
        }
    }

    private fun sendToRetry(message: Message, queue: String) {
        sendMessageTo(RabbitMqExchangeNameFormatter.retry("domain_events"), message, queue)
    }

    private fun sendToDeadLetter(message: Message, queue: String) {
        sendMessageTo("domain_events", message, queue)
    }

    private fun sendMessageTo(exchange: String?, message: Message, queue: String) {
        val headers = message.messageProperties.headers
        headers["redelivery_count"] = headers.getOrDefault("redelivery_count", 0) as Int + 1
        MessageBuilder.fromMessage(message).andProperties(
            MessagePropertiesBuilder.newInstance()
                .setContentEncoding("utf-8")
                .setContentType("application/json")
                .copyHeaders(headers)
                .build()
                                                         )
        publisher!!.publish(message, exchange!!, queue)
    }

    private fun hasBeenRedeliveredTooMuch(message: Message): Boolean {
        return message.messageProperties.headers.getOrDefault("redelivery_count", 0) as Int >= MAX_RETRIES
    }

    @Throws(Exception::class)
    private fun subscriberFor(queue: String): Any? {
        val queueParts = queue.split("\\.".toRegex()).toTypedArray()
        val subscriberName: String = Utils.toCamelFirstLower(queueParts[queueParts.size - 1])!!
        return try {
            val subscriber = context!!.getBean(subscriberName)
            domainEventSubscribers[queue] = subscriber
            subscriber
        } catch (e: Exception) {
            throw Exception(String.format("There are not registered subscribers for <%s> queue", queue))
        }
    }

    companion object {
        const val CONSUMER_NAME = "domain_events_consumer"
    }

}