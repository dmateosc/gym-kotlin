package gym.user.infrastructure.query.finder.model

import shared.domain.bus.query.Query

data class UserQueryPostgreSQL(
    val dni: String? =null,
    val name: String?=null,
    val first_lastname: String?=null,
    val second_lastname: String?=null,
    val age: Int?=null,
    val email: String?=null,
    val password: String?=null
) : Query