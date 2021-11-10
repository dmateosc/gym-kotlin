package com.example.testskotlin.repository

import com.example.testskotlin.repository.model.Auth
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthRepository: JpaRepository<Auth, Long> {

    fun findByNicknameAndPassWord(nickname: String, password: String): Auth?

    fun findByNickname(nickname: String?): Auth?


}