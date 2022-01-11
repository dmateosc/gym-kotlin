package gym.user.infrastructure.mapper

import gym.user.domain.model.*
import gym.user.infrastructure.controller.model.UserRequest

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