package com.example.testskotlin.user.application.create

import com.example.testskotlin.user.domain.model.*
import com.example.testskotlin.user.domain.repository.UserRepository
import java.util.*

class UserCreator(
    private val userRepository: UserRepository
) {
    fun create(
        name: String,
        firstLastName: String,
        secondLastName: String,
        email: String,
        age: Int,
        password: String,
        dni: String,
    ) {
        userRepository.save(
            User(
                UserId(UUID.randomUUID().toString()),
                Name(name),
                LastName(firstLastName),
                LastName(secondLastName),
                Email(email),
                Age(age),
                Password(password),
                DNI(dni)
            )
        )
    }
}