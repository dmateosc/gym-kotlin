package gym.user.domain.model

import gym.shared.domain.AggregateRoot
import gym.user.application.create.UserCreator
import gym.user.domain.exceptions.errors.UserException
import shared.domain.UserCreateDomainEvent

class User(
): AggregateRoot() {
    private  var userId: UserId? = null
    private lateinit var name: Name
    private lateinit var firstLastName: LastName
    private lateinit var secondLastName: LastName
    private lateinit var email: Email
    private lateinit var age: Age
    private lateinit var password: Password
    private lateinit var dni: DNI

    constructor(
        name: Name,
        firstLastName: LastName,
        secondLastName: LastName,
        email: Email,
        age: Age,
        password: Password,
        dni: DNI
    ) : this(){
        this.name = name
        this.secondLastName = secondLastName
        this.email = email
        this.firstLastName = firstLastName
        this.age = age
        this.password = password
        this.dni = dni
    }

    constructor(
        userId: UserId, name: Name,
        firstLastName: LastName,
        secondLastName: LastName,
        email: Email,
        age: Age,
        password: Password,
        dni: DNI
    ) : this(){
        this.userId = userId
        this.name = name
        this.secondLastName = secondLastName
        this.email = email
        this.firstLastName = firstLastName
        this.age = age
        this.password = password
        this.dni = dni
    }


    fun userId(): UserId{
        return userId!!
    }

    fun name(): Name{
        return name
    }
    fun firstLastName(): LastName{
        return firstLastName
    }

    fun secondLastName(): LastName{
        return secondLastName
    }

    fun email(): Email{
        return email
    }

    fun age(): Age{
        return age
    }

    fun password(): Password{
        return password
    }

    fun dni(): DNI{
        return dni
    }

    fun create(userId: UserId,
               name: Name,
               firstLastName: LastName,
               secondLastName: LastName,
               email: Email,
               age: Age,
               password: Password,
               dni: DNI
              ): User{
        var user = User(userId,
                name,
                firstLastName,
                secondLastName,
                email,
                age,
                password,
                dni)
        user.record(UserCreateDomainEvent(
            userId.value,
            name.value,
            firstLastName.value,
            secondLastName.value,
            email.value,
            age.value,
            password.value,
            dni.value))
        return user
    }
}

data class UserId(val value: String)

data class DNI(val value: String) {
    init {
        if (value.length != 9 || value[8].isDigit()) throw UserException.UserIsEmpty("digits length isn't correct")
    }
}

data class Name(val value: String) {
    init {
        value.takeIf { s -> s.isEmpty() }?. apply { throw UserException.UserIsEmpty("Name value is empty") }

    }
}

data class LastName(val value: String) {
    init {
        value.takeIf { s -> s.isEmpty() }?. apply { throw UserException.UserIsEmpty("lastname value is empty") }
    }
}

data class Email(val value: String) {
    init {
        value.takeIf { s -> s.isEmpty() }?. apply { throw UserException.UserIsEmpty("email value is empty") }
    }
}

data class Age(val value: Int)

data class Password(val value: String)


