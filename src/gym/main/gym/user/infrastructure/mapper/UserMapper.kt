package gym.user.infrastructure.mapper

import gym.user.application.find.UserResponse
import gym.user.domain.model.*
import gym.user.infrastructure.controller.model.UserRequest

class UserMapperRequest {

    fun toDomain(userRequest: UserRequest): User {

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

    fun fromDomain(user: User): UserRequest {
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

class UserMapperResponse{
    fun toDomain(userResponse: UserResponse): User {

        return User(
            Name(userResponse.name),
            LastName(userResponse.firstLastName),
            LastName(userResponse.secondLastName),
            Email(userResponse.email),
            Age(userResponse.age),
            Password(userResponse.password),
            DNI(userResponse.dni)
        )
    }

    fun fromDomain(user: User): UserResponse {
        return UserResponse(
            userId = user.userId().value,
            dni = user.dni().value,
            name = user.name().value,
            firstLastName = user.firstLastName().value,
            secondLastName = user.secondLastName().value,
            age = user.age().value,
            email = user.email().value,
            password = user.password().value
        )
    }

}