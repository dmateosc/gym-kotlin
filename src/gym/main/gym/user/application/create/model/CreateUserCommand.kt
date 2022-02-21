package gym.user.application.create.model

import shared.domain.bus.command.Command

data class CreateUserCommand(
    val dni: String,
    val name: String,
    val first_lastname: String,
    val second_lastname: String,
    val age: Int,
    val email: String,
    val password: String
                            ): Command