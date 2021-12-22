package com.example.testskotlin.user.infraestructure

import com.example.testskotlin.user.domain.model.Name
import com.example.testskotlin.user.domain.model.User
import com.example.testskotlin.user.domain.model.UserId
import com.example.testskotlin.user.domain.repository.UserRepository
import com.example.testskotlin.user.infraestructure.mapper.UserPostgreSQLMapper
import com.example.testskotlin.user.infraestructure.repository.UserCrudRepository
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.stereotype.Repository
import java.util.*


@Repository
class UserRepositoryPostgreSQL @Autowired constructor(private var userCrudRepository: UserCrudRepository) : UserRepository {


    override fun save(user: User) {
        takeIf { user.userId == null}?. apply { user.userId = UserId(UUID.randomUUID().toString())  }

        userCrudRepository.save(UserPostgreSQLMapper().dtoToEntity(user))
    }

    override fun findById(userId: UserId): User {
        return UserPostgreSQLMapper().entityToDto(userCrudRepository.findById(userId.value).get())
    }

    override fun findByName(name: Name): List<User> {
        return userCrudRepository.findByName(name = name.value).stream().map { UserPostgreSQLMapper().entityToDto(it) }.toList()
    }


}