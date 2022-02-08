package gym.user.infrastructure.repository

import gym.user.infrastructure.repository.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserCrudRepository : JpaRepository<UserEntity, String> {

    fun findByName(name: String): List<UserEntity>

}