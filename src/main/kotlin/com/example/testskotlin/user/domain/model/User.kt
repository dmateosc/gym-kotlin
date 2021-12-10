package com.example.testskotlin.user.domain.model

import java.lang.Exception
import java.util.*
import javax.persistence.Column
import javax.persistence.Id

class User(
    var userId: UserId,
    var name: Name,
    var firstLastName: LastName,
    var secondLastName: LastName,
    var email: Email,
    var age: Age,
    var password: Password,
    var dni: DNI
) {

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
        return User(userId, name, fistLastName,secondLastName, email,age, password,dni)
    }
}

data class UserId(val value: String)

data class DNI(val value: String){
    init {
        if(value.length!=9 || value[8].isDigit()) throw Exception()
    }
}

data class Name(val value: String) {
    init {
        value.takeIf { s -> s.isNotEmpty() }.apply { throw Exception() }
    }
}

data class LastName(val value: String) {
    init {
        value.takeIf { s -> s.isNotEmpty() }.apply { throw Exception() }
    }
}

data class Email(val value: String) {
    init {
        value.takeIf { s -> s.isBlank() }.apply { throw Exception() }
    }
}

data class Age(val value: Int)

data class Password(val value: String)


