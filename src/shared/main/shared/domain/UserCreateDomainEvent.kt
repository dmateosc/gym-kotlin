package shared.domain

import shared.infrastructure.config.DomainEventSubscriber
import java.io.Serializable
import java.util.HashMap

class UserCreateDomainEvent(): DomainEvent() {

    var userId: String? = null
    lateinit var name: String
    lateinit var firstLastName: String
    lateinit var secondLastName: String
    lateinit var email: String
    var age: Int = 0
    lateinit var password: String
    lateinit var dni: String

    constructor(
        userId: String?= "",
        name: String,
        firstLastName: String,
        secondLastName: String,
        email: String,
        age: Int,
        password: String,
        dni: String
               ) : this(){
        super.aggregateId= aggregateId
        this.userId = userId
        this.name = name
        this.secondLastName = secondLastName
        this.email = email
        this.firstLastName = firstLastName
        this.age = age
        this.password = password
        this.dni = dni
    }


    override fun eventName(): String? {
        return "user.created"
    }

    override fun toPrimitives(): HashMap<String, Serializable> {
        TODO("Not yet implemented")
    }

    override fun fromPrimitives(
        aggregateId: String?,
        body: HashMap<String?, Serializable?>?,
        eventId: String?,
        occurredOn: String?
                               ): DomainEvent? {
        TODO("Not yet implemented")
    }

}