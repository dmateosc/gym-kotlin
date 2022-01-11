package gym.user.infraestructure.repository

import com.example.testskotlin.user.domain.model.UserId
import com.example.testskotlin.user.infraestructure.repository.entity.UserMongo
import org.springframework.data.mongodb.repository.MongoRepository

interface UserMongoRepository: MongoRepository<UserMongo,String >  {
    fun findByUserId(userId: UserId): UserMongo

}