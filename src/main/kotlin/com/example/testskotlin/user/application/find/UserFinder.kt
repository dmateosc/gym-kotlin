package com.example.testskotlin.user.application.find

import com.example.testskotlin.user.domain.model.UserId
import com.example.testskotlin.user.domain.repository.UserRepository

class UserFinder(
    private val userRepository: UserRepository
) {

    fun finder(userId: UserId){
        userRepository.findById(userId)
    }

}