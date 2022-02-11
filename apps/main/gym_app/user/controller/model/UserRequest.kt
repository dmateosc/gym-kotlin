package gym_app.user.controller.model

data class UserRequest(
    val userId: String? = "",
    val dni: String? = "",
    val name: String? = "",
    val first_lastname: String? = "",
    val second_lastname: String? = "",
    val age: Int? = -1,
    val email: String? = "",
    val password: String? = ""
)