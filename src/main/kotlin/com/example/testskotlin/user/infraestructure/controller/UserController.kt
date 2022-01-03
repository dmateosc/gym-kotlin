package com.example.testskotlin.user.infraestructure.controller

import com.example.testskotlin.user.application.create.UserCreator
import com.example.testskotlin.user.application.find.UserFinder
import com.example.testskotlin.user.domain.model.UserId
import com.example.testskotlin.user.infraestructure.UserRepositoryMongoDB
import com.example.testskotlin.user.infraestructure.UserRepositoryPostgreSQL
import com.example.testskotlin.user.infraestructure.UserRepositoryPostgreSQL2
import com.example.testskotlin.user.infraestructure.controller.model.UserRequest
import com.example.testskotlin.user.infraestructure.controller.model.UserResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController{

    @Autowired private var userMongoDbRepository: UserRepositoryMongoDB
    @Autowired private var userRepositoryPostgreSQL: UserRepositoryPostgreSQL

    @Autowired private var userRepositoryPostgreSQL2: UserRepositoryPostgreSQL2


    @Autowired
    constructor(
        userRepositoryPostgreSQL: UserRepositoryPostgreSQL,
        userRepositoryPostgreSQL2: UserRepositoryPostgreSQL2,
        userMongoDbRepository: UserRepositoryMongoDB,

        ) {
        this.userMongoDbRepository = userMongoDbRepository
        this.userRepositoryPostgreSQL = userRepositoryPostgreSQL
        this.userRepositoryPostgreSQL2 = userRepositoryPostgreSQL2
    }

    @GetMapping
    fun userFinder(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
        val user = UserFinder(userRepositoryPostgreSQL).finder(UserId(userRequest.userId!!))
        return ResponseEntity(UserResponse(user.userId().value, user.name().value), HttpStatus.OK);
    }

    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest) {

        UserCreator(userRepositoryPostgreSQL).create(
            userRequest.name,
            userRequest.first_lastname,
            userRequest.second_lastname,
            userRequest.email,
            userRequest.age,
            userRequest.password,
            userRequest.dni
        )
        UserCreator(userRepositoryPostgreSQL2).create(
            userRequest.name,
            userRequest.first_lastname,
            userRequest.second_lastname,
            userRequest.email,
            userRequest.age,
            userRequest.password,
            userRequest.dni
                                                    )
        UserCreator(userMongoDbRepository).create(
            userRequest.name,
            userRequest.first_lastname,
            userRequest.second_lastname,
            userRequest.email,
            userRequest.age,
            userRequest.password,
            userRequest.dni
        )
    }

}