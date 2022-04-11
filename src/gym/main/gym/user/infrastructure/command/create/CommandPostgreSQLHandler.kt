package gym.user.infrastructure.command.create

import gym.shared.application.UUID
import gym.user.application.create.UserCreator
import gym.user.application.create.model.CreateUser
import gym.user.infrastructure.repository.UserRepositoryPostgreSQL
import gym.user.infrastructure.command.create.model.UserCommandPostgresSQL
import org.springframework.stereotype.Service
import shared.domain.bus.command.CommandHandler

@Service
class CommandPostgreSQLHandler(private val userRepository: UserRepositoryPostgreSQL) : CommandHandler<UserCommandPostgresSQL> {

    override fun handle(command: UserCommandPostgresSQL) {
        val userCreator = UserCreator(userRepository)
        userCreator.create(
            CreateUser(
                UUID().randomUUID(),
                command.name,
                command.first_lastname,
                command.second_lastname,
                command.email,
                command.age,
                command.password,
                command.dni
            )
                          )

    }
}