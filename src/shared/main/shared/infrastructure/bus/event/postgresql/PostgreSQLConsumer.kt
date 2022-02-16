package shared.infrastructure.bus.event.postgresql

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import shared.domain.DomainEvent
import shared.domain.Utils
import shared.infrastructure.bus.event.DomainEventsInformation
import shared.infrastructure.bus.event.SpringEventBus
import shared.infrastructure.persistence.DomainEventRepository
import java.sql.Timestamp
import java.util.*
import javax.transaction.Transactional
import kotlin.reflect.KClass

@Service
open class PostgreSQLConsumer(
    @Autowired private val domainEventRepository: DomainEventRepository,
    private val bus: SpringEventBus,
    private val domainEventsInformation: DomainEventsInformation
                             ) {

    private var isStopped: Boolean = true

    @Transactional
    open fun consume() {
//        while (!isStopped) {
            val events = domainEventRepository.findAllOrderByOccurred_on()

            events.forEach {
                executeSubscribers(
                    it[0] as String,
                    it[1] as String,
                    it[2] as String,
                    it[3] as String,
                    it[4] as Timestamp
                                  )
            }
            domainEventRepository.deleteAll()
//        }
    }

    fun stop(){
        this.isStopped = false
    }

    private fun executeSubscribers(
        id: String, aggregateId: String, eventName: String, body: String, occurredOn: Timestamp
                                  ) {
        val domainEventClass: KClass<out DomainEvent> = domainEventsInformation.forName(eventName)

        val nullInstance: DomainEvent = domainEventClass::class.java.getConstructor().newInstance().objectInstance!!

        val fromPrimitivesMethod = domainEventClass::class.java.getMethod(
            "fromPrimitives",
            String::class.java,
            HashMap::class.java,
            String::class.java,
            String::class.java
                                                             )

        val domainEvent = fromPrimitivesMethod.invoke(
            nullInstance,
            aggregateId,
            Utils.jsonDecode(body),
            id,
            Utils.dateToString(occurredOn)
                                                     )

        bus.publish(listOf(domainEvent as DomainEvent))


    }


}