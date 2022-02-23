package gym.user.application.create

import gym.user.application.create.model.CreateUser
import gym.user.domain.model.*
import gym.user.domain.repository.UserRepository
import shared.domain.Service


@Service
class UserCreator(
    private val userRepository: UserRepository
                 ) {

    fun create(
        event: CreateUser
              ) {
        val user = User(
            UserId(event.userId!!),
            Name(event.name),
            LastName(event.firstLastName),
            LastName(event.secondLastName),
            Email(event.email),
            Age(event.age),
            Password(event.password),
            DNI(event.dni)
        )

        userRepository.save(
            user
                           )
    }
}