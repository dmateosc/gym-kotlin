package gym.user.application.create

import gym.user.application.create.model.CreateUserCommand
import gym.user.domain.model.*
import org.springframework.stereotype.Service
import shared.domain.bus.command.CommandHandler
import java.util.*

@Service
class UserCreatorCommandHandler(private val userCreator: UserCreator) : CommandHandler<CreateUserCommand> {
    override fun handle(command: CreateUserCommand) {

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