package gym.user.application.find

import shared.infrastructure.bus.query.Response

data class UserResponse (
    val userId: String? = null,
    val name: String,
    val firstLastName: String,
    val secondLastName: String,
    val email: String,
    val age: Int = 0,
    val password: String,
    val dni: String,
) : Response {

}