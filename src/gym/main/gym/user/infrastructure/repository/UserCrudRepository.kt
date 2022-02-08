package gym.user.infrastructure.repository

import gym.user.infrastructure.repository.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserCrudRepository : JpaRepository<UserEntity, String> {

    fun findByName(name: String): List<UserEntity>

}