package gym.user.infrastructure.repository

import gym.user.domain.model.UserId
import gym.user.infrastructure.repository.entity.UserMongo
import org.springframework.data.mongodb.repository.MongoRepository


interface UserMongoRepository: MongoRepository<UserMongo,String >  {
    fun findByUserId(userId: UserId): UserMongo

}