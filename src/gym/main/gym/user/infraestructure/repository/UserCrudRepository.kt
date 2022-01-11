package gym.user.infraestructure.repository

import com.example.testskotlin.user.infraestructure.repository.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserCrudRepository : CrudRepository<UserEntity, String> {

    fun findByName(name: String): List<UserEntity>

}