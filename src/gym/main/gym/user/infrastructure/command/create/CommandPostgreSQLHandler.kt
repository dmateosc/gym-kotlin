package gym.user.infrastructure.command.create

import gym.user.application.create.UserCreator
import gym.user.domain.model.*
import gym.user.infrastructure.UserRepositoryPostgreSQL
import gym.user.infrastructure.command.create.model.UserCommandPostgresSQL
import org.springframework.stereotype.Service
import shared.domain.bus.command.CommandHandler
import java.util.*

@Service
class CommandPostgreSQLHandler(private val userRepository: UserRepositoryPostgreSQL) : CommandHandler<UserCommandPostgresSQL> {

    override fun handle(command: UserCommandPostgresSQL) {
        val userCreator = UserCreator(userRepository)
        userCreator.create(
            User(
                UserId(UUID.randomUUID().toString()),
                Name(command.name),
                LastName(command.first_lastname),
                LastName(command.second_lastname),
                Email(command.email),
                Age(command.age),
                Password(command.password),
                DNI(command.dni)
                )
                          )

    }
}