package com.example.testskotlin.user.infraestructure.controller

import com.example.testskotlin.user.application.create.UserCreator
import com.example.testskotlin.user.application.find.UserFinder
import com.example.testskotlin.user.domain.model.UserId
import com.example.testskotlin.user.domain.repository.UserRepository
import com.example.testskotlin.user.infraestructure.controller.model.UserRequest
import com.example.testskotlin.user.infraestructure.UserRepositoryPostgreSQL
import com.example.testskotlin.user.infraestructure.controller.model.UserResponse
import com.example.testskotlin.user.infraestructure.mapper.UserRequestMapper
import com.example.testskotlin.user.infraestructure.repository.UserCrudRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

@Controller
class UserController{
    private var userRepository: UserRepository

    @Autowired
    constructor(userRepository: UserRepositoryPostgreSQL){
        this.userRepository = userRepository
    }

    @GetMapping
    fun userFinder(@RequestBody userRequest: UserRequest) : ResponseEntity<UserResponse>{
        val user = UserFinder(userRepository).finder(UserId(userRequest.userId))
        return ResponseEntity(UserResponse(user.userId.value, user.name.value),HttpStatus.OK);
    }
    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest){
        UserCreator(userRepository).create(UserRequestMapper().entityToDto(userRequest))
    }

}