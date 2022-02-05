package gym.user.application.create

import gym.user.application.create.model.CreateUserRequest
import gym.user.domain.model.*
import gym.user.domain.repository.UserRepository
import shared.domain.Service

import java.util.*


@Service
class UserCreator(
    private val userRepository: UserRepository
) {
    fun create(
        createUserRequest: CreateUserRequest
    ) {
        userRepository.save(
            User(
                UserId(UUID.randomUUID().toString()),
                Name(createUserRequest.name),
                LastName(createUserRequest.first_lastname),
                LastName(createUserRequest.second_lastname),
                Email(createUserRequest.email),
                Age(createUserRequest.age),
                Password(createUserRequest.password),
                DNI(createUserRequest.dni)
            )
        )
    }
}