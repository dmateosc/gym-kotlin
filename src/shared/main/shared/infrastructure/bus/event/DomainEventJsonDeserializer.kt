package shared.infrastructure.bus.event

import shared.domain.DomainEvent
import shared.domain.Service
import shared.domain.Utils
import java.io.Serializable
import java.lang.reflect.InvocationTargetException
import kotlin.reflect.KClass

@Service
class DomainEventJsonDeserializer {
    private var information: DomainEventsInformation? = null

    fun DomainEventJsonDeserializer(information: DomainEventsInformation?) {
        this.information = information
    }

    @Throws(
        InvocationTargetException::class,
        IllegalAccessException::class,
        NoSuchMethodException::class,
        InstantiationException::class
           )
    fun deserialize(body: String?): DomainEvent? {
        val eventData: HashMap<String, Serializable> = Utils.jsonDecode(body!!)
        val data = eventData["data"] as HashMap<String, Serializable>?
        val attributes = data!!["attributes"] as HashMap<String, Serializable>?
        val domainEventClass: KClass<out DomainEvent> = information!!.forName((data["type"] as String?)!!)
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
            attributes!!["id"] as String?,
            attributes,
            data["id"] as String?,
            data["occurred_on"] as String?
                                                     )
        return domainEvent as DomainEvent
    }
}