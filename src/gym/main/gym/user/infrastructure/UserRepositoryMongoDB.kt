package gym.user.infrastructure

import gym.user.domain.model.Name
import gym.user.domain.model.User
import gym.user.domain.model.UserId
import gym.user.domain.repository.UserRepository
import gym.user.infrastructure.mapper.UserMongoDbMapper
import gym.user.infrastructure.repository.UserMongoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class UserRepositoryMongoDB @Autowired constructor(private var userMongoRepository: UserMongoRepository) :
        UserRepository {
    override fun save(user: User) {
        userMongoRepository.save(UserMongoDbMapper().dtoToEntity(user))
    }

    override fun findById(userId: UserId): User {
        return UserMongoDbMapper().entityToDto(userMongoRepository.findByUserId(userId))
    }

    override fun findByName(name: Name): List<User> {
        TODO()
    }

}