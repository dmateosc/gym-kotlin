package gym.user.infrastructure.command.create

import gym.shared.application.UUID
import gym.user.application.create.UserCreator
import gym.user.application.create.model.CreateUser
import gym.user.domain.model.*
import gym.user.infrastructure.UserRepositoryMongoDB
import gym.user.infrastructure.command.create.model.UserCommandMongo
import org.springframework.stereotype.Service
import shared.domain.bus.command.CommandHandler
import java.util.*

@Service
class CommandMongoHandler(private val userRepository: UserRepositoryMongoDB) : CommandHandler<UserCommandMongo> {

    override fun handle(command: UserCommandMongo) {
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