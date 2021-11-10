package com.example.testskotlin.service

import com.example.testskotlin.repository.UserRepository
import com.example.testskotlin.repository.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun getUsers(): User {
        return userRepository.findAll().first();
    }

}