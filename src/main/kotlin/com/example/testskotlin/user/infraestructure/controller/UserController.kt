package com.example.testskotlin.user.infraestructure.controller

import com.example.testskotlin.user.application.find.UserFinder
import com.example.testskotlin.user.domain.model.UserId
import com.example.testskotlin.user.domain.repository.UserRepository
import com.example.testskotlin.user.infraestructure.controller.model.UserRequest
import com.example.testskotlin.user.infraestructure.UserRepositoryPostgreSQL
import com.example.testskotlin.user.infraestructure.repository.UserCrudRepository
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class UserController{
    private lateinit var userRepository: UserRepository

    @Autowired
    constructor(userRepository: UserRepositoryPostgreSQL){
        this.userRepository = userRepository
    }

    fun userFinder(userRequest: UserRequest){
        UserFinder(userRepository).finder(UserId(userRequest.userId))
    }

}