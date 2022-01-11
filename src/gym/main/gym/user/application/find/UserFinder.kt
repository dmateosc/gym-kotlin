package gym.user.application.find

import com.example.testskotlin.user.domain.model.User
import com.example.testskotlin.user.domain.model.UserId
import com.example.testskotlin.user.domain.repository.UserRepository

class UserFinder(
    private val userRepository: UserRepository
) {

    fun finder(userId: UserId): User{
        return userRepository.findById(userId)
    }

}