package gym.user.infrastructure.command.create.model

import shared.domain.bus.command.Command

data class UserCommandPostgresSQL(
    val dni: String,
    val name: String,
    val first_lastname: String,
    val second_lastname: String,
    val age: Int,
    val email: String,
    val password: String
                            ): Command