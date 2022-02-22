package gym.user.infrastructure.bus

import gym.user.application.create.UserCreator
import gym.user.domain.model.*
import gym.user.domain.repository.UserRepository
import gym.user.infrastructure.UserRepositoryMongoDB
import gym.user.infrastructure.UserRepositoryPostgreSQL
import org.springframework.context.event.EventListener
import shared.domain.DomainEvent
import shared.domain.Service
import shared.domain.UserCreateDomainEvent
import shared.infrastructure.config.DomainEventSubscriber


@Service
@DomainEventSubscriber([UserCreateDomainEvent::class])
class UserCreatorPostgreSQL(private val userRepository: UserRepositoryPostgreSQL) {

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
        UserCreator(userRepository).create(
            user
        )
    }
}