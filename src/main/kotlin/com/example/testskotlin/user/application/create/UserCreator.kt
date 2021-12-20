package com.example.testskotlin.user.application.create

import com.example.testskotlin.user.domain.model.User
import com.example.testskotlin.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserCreator(
    private val userRepository: UserRepository
) {
    fun create(user: User){
        userRepository.save(user)
    }
}