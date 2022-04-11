package gym.user.infrastructure.bus

import gym.shared.application.UUID
import gym.user.application.create.UserCreator
import gym.user.application.create.model.CreateUser
import gym.user.application.create.model.UserCreateDomainEvent
import gym.user.infrastructure.repository.UserRepositoryPostgreSQL
import org.springframework.context.event.EventListener
import shared.domain.Service
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