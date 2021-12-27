package com.example.testskotlin.user.infraestructure.mapper

import com.example.testskotlin.user.domain.model.*
import com.example.testskotlin.user.infraestructure.controller.model.UserRequest
import com.example.testskotlin.user.infraestructure.repository.entity.UserEntity

class UserRequestMapper {

    fun entityToDto(userRequest: UserRequest): User {

        return User(
            Name(userRequest.name),
            LastName(userRequest.first_lastname),
            LastName(userRequest.second_lastname),
            Email(userRequest.email),
            Age(userRequest.age),
            Password(userRequest.password),
            DNI(userRequest.dni)
        )
    }

    fun dtoToEntity(user: User): UserRequest {
        return UserRequest(
            userId = user.userId().value,
            dni = user.dni().value,
            name = user.name().value,
            first_lastname = user.firstLastName().value,
            second_lastname = user.secondLastName().value,
            age = user.age().value,
            email = user.email().value,
            password = user.password().value
        )
    }

}