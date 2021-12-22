package com.example.testskotlin.user.infraestructure.controller

import com.example.testskotlin.user.application.create.UserCreator
import com.example.testskotlin.user.application.find.UserFinder
import com.example.testskotlin.user.domain.model.UserId
import com.example.testskotlin.user.domain.repository.UserRepository
import com.example.testskotlin.user.infraestructure.UserRepositoryMongoDB
import com.example.testskotlin.user.infraestructure.UserRepositoryPostgreSQL
import com.example.testskotlin.user.infraestructure.controller.model.UserRequest
import com.example.testskotlin.user.infraestructure.controller.model.UserResponse
import com.example.testskotlin.user.infraestructure.mapper.UserRequestMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController{



    private var userRepositoryPostgreSQL: UserRepository
    private var userMongoDbRepository: UserRepository

    @Qualifier("userCreatorSql")
    private var  userCreatorSql: UserCreator
    @Qualifier("userCreatorMongo")
    private var userCreatorMongo: UserCreator

    @Autowired
    constructor(userRepositoryPostgreSQL: UserRepositoryPostgreSQL,
                userMongoDbRepository: UserRepositoryMongoDB,

    ){
        this.userRepositoryPostgreSQL = userRepositoryPostgreSQL
        this.userMongoDbRepository = userMongoDbRepository
        this.userCreatorSql = UserCreator(userRepositoryPostgreSQL)
        this.userCreatorMongo = UserCreator(userMongoDbRepository)
    }

    @GetMapping
    fun userFinder(@RequestBody userRequest: UserRequest) : ResponseEntity<UserResponse>{
        val user = UserFinder(userRepositoryPostgreSQL).finder(UserId(userRequest.userId!!))
        return ResponseEntity(UserResponse(user.userId!!.value, user.name.value),HttpStatus.OK);
    }
    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest){
        userCreatorSql.create(UserRequestMapper().entityToDto(userRequest))
        userCreatorMongo.create(UserRequestMapper().entityToDto(userRequest))
    }

}