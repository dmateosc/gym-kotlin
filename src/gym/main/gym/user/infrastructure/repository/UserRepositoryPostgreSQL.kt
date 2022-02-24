package gym.user.infrastructure.repository

import gym.user.domain.model.DNI
import gym.user.domain.model.Name
import gym.user.domain.model.User
import gym.user.domain.model.UserId
import gym.user.domain.repository.UserRepository
import gym.user.infrastructure.exceptions.UserException
import gym.user.infrastructure.mapper.UserPostgreSQLMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository


@Repository
@Primary
open class UserRepositoryPostgreSQL @Autowired constructor(private var userCrudRepository: UserCrudRepository) :
        UserRepository {


    override fun save(user: User) {
        kotlin.runCatching {
            userCrudRepository.save(UserPostgreSQLMapper().dtoToEntity(user))
        }.onFailure {
            throw UserException.UserSQLException("SQL exception saving ${it.message}")
        }
    }

    override fun findById(userId: UserId): User {
        return UserPostgreSQLMapper().entityToDto(userCrudRepository.findById(userId.value).get())
    }

    override fun findByDNI(dni: DNI): User {
        return UserPostgreSQLMapper().entityToDto(userCrudRepository.findByDni(dni.value))
    }

    override fun findByName(name: Name): List<User> {
        return userCrudRepository.findByName(name = name.value).map { userEntity ->
            UserPostgreSQLMapper().entityToDto(userEntity)
        }


    }


}