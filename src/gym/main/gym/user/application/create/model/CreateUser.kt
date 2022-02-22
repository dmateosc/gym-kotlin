package gym.user.application.create.model

data class CreateUser(
    val userId: String? = null,
    val name: String,
    val firstLastName: String,
    val secondLastName: String,
    val email: String,
    val age: Int = 0,
    val password: String,
    val dni: String,
)
