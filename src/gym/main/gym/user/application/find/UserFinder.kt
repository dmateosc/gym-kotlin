package gym.user.application.find

import gym.user.domain.model.User
import gym.user.domain.model.UserId
import gym.user.domain.repository.UserRepository

class UserFinder(
    private val userRepository: UserRepository
) {

    fun finder(userId: UserId): User{
        return userRepository.findById(userId)
    }

}