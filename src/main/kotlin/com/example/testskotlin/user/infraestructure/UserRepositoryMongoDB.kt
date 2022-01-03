package com.example.testskotlin.user.infraestructure

import com.example.testskotlin.user.domain.model.Name
import com.example.testskotlin.user.domain.model.User
import com.example.testskotlin.user.domain.model.UserId
import com.example.testskotlin.user.domain.repository.UserRepository
import com.example.testskotlin.user.infraestructure.mapper.UserMongoDbMapper
import com.example.testskotlin.user.infraestructure.repository.UserMongoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository

@Repository
@Primary
class UserRepositoryMongoDB @Autowired constructor(private var userMongoRepository: UserMongoRepository) : UserRepository {
    override fun save(user: User) {
        userMongoRepository.save(UserMongoDbMapper().dtoToEntity(user))
    }

    override fun findById(userId: UserId): User {
        return UserMongoDbMapper().entityToDto(userMongoRepository.findByUserId(userId))
    }

    override fun findByName(name: Name): List<User> {
        TODO("Not yet implemented")
    }

}