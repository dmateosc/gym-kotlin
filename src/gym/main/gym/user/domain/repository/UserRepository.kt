package gym.user.domain.repository

import com.example.testskotlin.user.domain.model.Name
import com.example.testskotlin.user.domain.model.User
import com.example.testskotlin.user.domain.model.UserId

interface UserRepository {

    fun save(user: User)
    fun findById(userId: UserId): User
    fun findByName(name: Name): List<User>

}