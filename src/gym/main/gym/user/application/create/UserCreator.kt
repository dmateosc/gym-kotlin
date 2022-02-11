package gym.user.application.create

import gym.user.domain.model.*
import gym.user.domain.repository.UserRepository
import org.springframework.context.event.EventListener
import shared.domain.Service
import shared.domain.UserCreateDomainEvent


@Service
class UserCreator(
    private val userRepository: UserRepository
                 ) {

    @EventListener
    fun on(event: UserCreateDomainEvent) {
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
        create(user)
    }


    fun create(
        user: User
              ) {
        userRepository.save(
            user
                           )
    }
}