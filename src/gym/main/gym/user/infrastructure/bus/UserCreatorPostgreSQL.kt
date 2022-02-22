package gym.user.infrastructure.bus

import gym.shared.application.UUID
import gym.user.application.create.UserCreator
import gym.user.application.create.model.CreateUser
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

        val user = CreateUser(
            UUID().randomUUID(),
            event.name,
            event.firstLastName,
            event.secondLastName,
            event.email,
            event.age,
            event.password,
            event.dni
        )

        UserCreator(userRepository).create(
            user
        )
    }
}