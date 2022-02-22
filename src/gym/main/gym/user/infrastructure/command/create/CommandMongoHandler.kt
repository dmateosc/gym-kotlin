package gym.user.infrastructure.command.create

import gym.user.application.create.UserCreator
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