package gym.user.infrastructure.repository

import gym.user.infrastructure.repository.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserCrudRepository : CrudRepository<UserEntity, String> {

    fun findByName(name: String): MutableList<UserEntity>

}