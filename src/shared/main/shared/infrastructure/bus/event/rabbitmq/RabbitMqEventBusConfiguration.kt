package shared.infrastructure.bus.event.rabbitmq

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import shared.infrastructure.bus.event.DomainEventSubscribersInformation
import shared.infrastructure.bus.event.DomainEventsInformation
import shared.infrastructure.config.Parameter
import shared.infrastructure.config.ParameterNotExist
import java.util.stream.Collectors

@Configuration
open class RabbitMqEventBusConfiguration(
    private var domainEventSubscribersInformation: DomainEventSubscribersInformation,
    private var domainEventsInformation: DomainEventsInformation,
    private var config: Parameter
) {
    private var exchangeName: String

    init {
        exchangeName = config["RABBITMQ_EXCHANGE"]
    }



    @Bean
    @Throws(ParameterNotExist::class)
    open fun connection(): ConnectionFactory {
        val factory = CachingConnectionFactory()
        factory.host = config!!["RABBITMQ_HOST"]
        factory.port = config!!.getInt("RABBITMQ_PORT")!!
        factory.username = config!!["RABBITMQ_LOGIN"]
        factory.setPassword(config!!["RABBITMQ_PASSWORD"])
        return factory
    }

    @Bean
    open fun declaration(): Declarables{
        connection().createConnection()

        val retryExchangeName = RabbitMqExchangeNameFormatter.retry(exchangeName)
        val deadLetterExchangeName = RabbitMqExchangeNameFormatter.deadLetter(exchangeName)

        val domainEventsExchange =TopicExchange(exchangeName,true, false)
        val retryDomainEventsExchange =TopicExchange(retryExchangeName,true, false)
        val deadLetterDomainEventsExchange =TopicExchange(deadLetterExchangeName,true, false)

        val declarable = mutableListOf<Declarable>(
            domainEventsExchange,retryDomainEventsExchange,deadLetterDomainEventsExchange
                                    )
        val queueAndBindings = declareQueuesAndBindings(
            domainEventsExchange,
            retryDomainEventsExchange,
            deadLetterDomainEventsExchange).stream().flatMap { it.stream() }.collect(Collectors.toList())
        declarable.addAll(queueAndBindings)

        return Declarables(declarable)


    }

    private fun declareQueuesAndBindings(
        domainEventsExchange: TopicExchange,
        retryDomainEventsExchange: TopicExchange,
        deadLetterDomainEventsExchange: TopicExchange
                                             ): Collection<Collection<Declarable>> {
        return domainEventSubscribersInformation.all().map { information ->
            val queueName: String = RabbitMqQueueNameFormatter.format(information)
            val retryQueueName: String = RabbitMqQueueNameFormatter.formatRetry(information)
            val deadLetterQueueName: String = RabbitMqQueueNameFormatter.formatDeadLetter(information)
            val queue = QueueBuilder.durable(queueName).build()
            val retryQueue = QueueBuilder.durable(retryQueueName).withArguments(
                retryQueueArguments(domainEventsExchange, queueName)
                                                                               )
                .build()
            val deadLetterQueue = QueueBuilder.durable(deadLetterQueueName).build()
            val fromExchangeSameQueueBinding = BindingBuilder
                .bind(queue)
                .to(domainEventsExchange)
                .with(queueName)
            val fromRetryExchangeSameQueueBinding = BindingBuilder
                .bind(retryQueue)
                .to(retryDomainEventsExchange)
                .with(queueName)
            val fromDeadLetterExchangeSameQueueBinding = BindingBuilder
                .bind(deadLetterQueue)
                .to(deadLetterDomainEventsExchange)
                .with(queueName)
            val fromExchangeDomainEventsBindings: List<Binding> =
                information.subscribedEvents().map { domainEventClass ->
                    val eventName = domainEventsInformation.forClass(domainEventClass)
                    BindingBuilder
                        .bind(queue)
                        .to(domainEventsExchange)
                        .with(eventName)
                }.toList()
            val queuesAndBindings: MutableList<Declarable> = ArrayList()
            queuesAndBindings.add(queue)
            queuesAndBindings.add(fromExchangeSameQueueBinding)
            queuesAndBindings.addAll(fromExchangeDomainEventsBindings)
            queuesAndBindings.add(retryQueue)
            queuesAndBindings.add(fromRetryExchangeSameQueueBinding)
            queuesAndBindings.add(deadLetterQueue)
            queuesAndBindings.add(fromDeadLetterExchangeSameQueueBinding)
            queuesAndBindings
        }.toList()
    }

    private fun retryQueueArguments(exchange: TopicExchange, routingKey: String): HashMap<String?, Any?>? {
        return object : HashMap<String?, Any?>() {
            init {
                put("x-dead-letter-exchange", exchange.name)
                put("x-dead-letter-routing-key", routingKey)
                put("x-message-ttl", 1000)
            }
        }
    }


}