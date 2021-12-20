package com.example.testskotlin.user.domain.model

import java.lang.Exception

class User(
) {
    var userId: UserId? = null
    lateinit var name: Name
    lateinit var firstLastName: LastName
    lateinit var secondLastName: LastName
    lateinit var email: Email
    lateinit var age: Age
    lateinit var password: Password
    lateinit var dni: DNI

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

    fun create(
        userId: UserId,
        name: Name,
        fistLastName: LastName,
        secondLastName: LastName,
        dni: DNI,
        age: Age,
        email: Email,
        password: Password
    ): User {
        return User(userId, name, fistLastName, secondLastName, email, age, password, dni)
    }
}

data class UserId(val value: String)

data class DNI(val value: String) {
    init {
        if (value.length != 9 || value[8].isDigit()) throw Exception("digits lenght isn't correct")
    }
}

data class Name(val value: String) {
    init {
        value.takeIf { s -> s.isEmpty() }?. apply { throw Exception("Name value is empty") }

    }
}

data class LastName(val value: String) {
    init {
        value.takeIf { s -> s.isEmpty() }?. apply { throw Exception("lastname value is empty") }
    }
}

data class Email(val value: String) {
    init {
        value.takeIf { s -> s.isEmpty() }?. apply { throw Exception("email value is empty") }
    }
}

data class Age(val value: Int)

data class Password(val value: String)


