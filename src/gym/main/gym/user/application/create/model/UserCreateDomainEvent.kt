package gym.user.application.create.model

import shared.domain.DomainEvent
import java.io.Serializable
import java.util.*

class UserCreateDomainEvent(aggregateId: String?): DomainEvent(aggregateId) {

    var userId: String? = null
    lateinit var name: String
    lateinit var firstLastName: String
    lateinit var secondLastName: String
    lateinit var email: String
    var age: Int = 0
    lateinit var password: String
    lateinit var dni: String

    constructor() : this("")

    constructor(
        userId: String?= UUID.randomUUID().toString(),
        name: String,
        firstLastName: String,
        secondLastName: String,
        email: String,
        age: Int,
        password: String,
        dni: String
               ) : this(userId){
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
        return hashMapOf(
            "userId" to this.userId!!,
            "name" to this.name,
            "secondLastName" to this.secondLastName,
            "email" to this.email,
            "firstLastName" to this.firstLastName,
            "age" to this.age,
            "password" to this.password,
            "dni" to this.dni
        )
    }

    override fun fromPrimitives(
        aggregateId: String,
        body: HashMap<String, Serializable>,
        eventId: String,
        occurredOn: String
                               ): DomainEvent {
        return UserCreateDomainEvent(
            name = body["name"] as String,
            firstLastName = body["firstLastName"] as String,
            secondLastName = body["secondLastName"] as String,
            email = body["email"] as String,
            age = body["age"] as Int,
            password = body["password"] as String,
            dni =  body["dni"]as String)
    }

}