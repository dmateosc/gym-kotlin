package shared.infrastructure.bus.event.rabbitmq

object RabbitMqExchangeNameFormatter {

    fun retry(exchangeName: String?): String? {
        return String.format("retry-%s", exchangeName)
    }

    fun deadLetter(exchangeName: String?): String? {
        return String.format("dead_letter-%s", exchangeName)
    }

}