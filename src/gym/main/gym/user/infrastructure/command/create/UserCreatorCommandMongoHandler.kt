package gym.user.infrastructure.command.create

import gym.user.application.create.UserCreator
import gym.user.domain.model.*
import gym.user.infrastructure.UserRepositoryMongoDB
import gym.user.infrastructure.command.create.model.CreateUserCommandMongo
import gym.user.infrastructure.repository.UserMongoRepository
import org.springframework.stereotype.Service
import shared.domain.bus.command.CommandHandler
import java.util.*

@Service
class UserCreatorCommandMongoHandler(private val userRepository: UserRepositoryMongoDB) : CommandHandler<CreateUserCommandMongo> {

    override fun handle(command: CreateUserCommandMongo) {
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