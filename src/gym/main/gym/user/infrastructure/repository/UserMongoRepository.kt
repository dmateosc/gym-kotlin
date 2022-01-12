package gym.user.infrastructure.repository

import gym.user.domain.model.UserId
import gym.user.infrastructure.repository.entity.UserMongo
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository


interface UserMongoRepository: MongoRepository<UserMongo,String >  {
    fun findByUserId(userId: UserId): UserMongo

}