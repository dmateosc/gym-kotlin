package gym.user.application.create

import gym.user.domain.model.*
import gym.user.domain.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserCreator(
    private val userRepository: UserRepository
) {
    fun create(
        name: String,
        firstLastName: String,
        secondLastName: String,
        email: String,
        age: Int,
        password: String,
        dni: String,
    ) {
        userRepository.save(
            User(
                UserId(UUID.randomUUID().toString()),
                Name(name),
                LastName(firstLastName),
                LastName(secondLastName),
                Email(email),
                Age(age),
                Password(password),
                DNI(dni)
            )
        )
    }
}