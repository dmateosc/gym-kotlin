package com.example.testskotlin.user.domain.repository

import com.example.testskotlin.user.domain.model.Name
import com.example.testskotlin.user.domain.model.User
import com.example.testskotlin.user.domain.model.UserId
import org.springframework.stereotype.Repository

@Repository
interface UserRepository {

    fun save(user: User)
    fun findById(userId: UserId): User
    fun findByName(name: Name): List<User>

}