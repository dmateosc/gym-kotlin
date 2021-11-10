package com.example.testskotlin.service

import com.example.testskotlin.controller.model.AuthUserDto
import com.example.testskotlin.repository.AuthRepository
import com.example.testskotlin.repository.UserRepository
import com.example.testskotlin.repository.model.Auth
import com.example.testskotlin.repository.model.User

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthService: UserDetailsService{

    @Autowired
    lateinit var authRepository: AuthRepository
    @Autowired
    lateinit var userRepository: UserRepository


    fun findByNickname(nickname: String): Auth {
        return authRepository.findByNickname(nickname)!!
    }

    fun createUser(authUserDto: AuthUserDto){

        val user = User()
        user.name = authUserDto.user!!.name
        user.lastname = authUserDto.user.lastname
        user.email = authUserDto.user.email

        userRepository.save(user).userId

        val auth = Auth()
        auth.userId = userRepository.save(user).userId
        auth.nickname = authUserDto.nickname!!
        auth.passWord = authUserDto.password!!
        authRepository.save(auth)
    }


    override fun loadUserByUsername(username: String?): UserDetails {
        val auth = authRepository.findByNickname(username)!!
        return org.springframework.security.core.userdetails.User(auth.nickname, auth.passWord, ArrayList() )
    }

}