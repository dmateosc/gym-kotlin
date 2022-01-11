package gym.user.infrastructure

import gym.user.domain.model.Name
import gym.user.domain.model.UserId
import gym.user.infrastructure.exceptions.UserException
import gym.user.domain.model.User
import gym.user.domain.repository.UserRepository
import gym.user.infrastructure.mapper.UserPostgreSQLMapper
import gym.user.infrastructure.repository.UserCrudRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepositoryPostgreSQL @Autowired constructor(private var userCrudRepository: UserCrudRepository) : UserRepository {


    override fun save(user: User) {
        kotlin.runCatching {
        userCrudRepository.save(UserPostgreSQLMapper().dtoToEntity(user))
        }.onFailure {
            throw UserException.UserSQLException("SQL exception saving")
        }
    }

    override fun findById(userId: UserId): User {
        return UserPostgreSQLMapper().entityToDto(userCrudRepository.findById(userId.value).get())
    }

    override fun findByName(name: Name): MutableList<User> =
        userCrudRepository.findByName(name = name.value).stream().map { UserPostgreSQLMapper().entityToDto(it) }
            .toList()



}