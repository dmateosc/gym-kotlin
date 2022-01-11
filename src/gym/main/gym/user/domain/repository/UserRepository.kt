package gym.user.domain.repository

import gym.user.domain.model.Name
import gym.user.domain.model.User
import gym.user.domain.model.UserId

interface UserRepository {

    fun save(user: User)
    fun findById(userId: UserId): User
    fun findByName(name: Name): List<User>

}