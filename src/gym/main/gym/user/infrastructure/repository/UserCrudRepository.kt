package gym.user.infrastructure.repository

import gym.user.domain.model.DNI
import gym.user.infrastructure.repository.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserCrudRepository : JpaRepository<UserEntity, String> {

    fun findByName(name: String): List<UserEntity>
    fun findByDni(dni: String): UserEntity


}