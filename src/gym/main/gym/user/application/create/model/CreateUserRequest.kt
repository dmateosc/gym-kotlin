package gym.user.application.create.model

data class CreateUserRequest (
    val dni: String,
    val name: String,
    val first_lastname: String,
    val second_lastname: String,
    val age: Int,
    val email: String,
    val password: String
)