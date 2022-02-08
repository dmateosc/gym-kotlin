package gym.user.domain.repository

import gym.user.domain.model.Name
import gym.user.domain.model.User
import gym.user.domain.model.UserId
import org.springframework.stereotype.Repository

@Repository
interface UserRepository {

    fun save(user: User)
    fun findById(userId: UserId): User
    fun findByName(name: Name): List<User>

}