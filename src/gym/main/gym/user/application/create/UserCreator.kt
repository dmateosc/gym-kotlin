package gym.user.application.create

import gym.user.domain.model.*
import gym.user.domain.repository.UserRepository
import org.springframework.context.event.EventListener
import shared.domain.DomainEvent
import shared.domain.Service
import shared.domain.UserCreateDomainEvent
import shared.infrastructure.config.DomainEventSubscriber


@Service
@DomainEventSubscriber([UserCreateDomainEvent::class])
class UserCreator(
    private val userRepository: UserRepository
                 ) {

    fun create(
        user: User
              ) {
        userRepository.save(
            user
                           )
    }
}