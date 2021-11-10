package com.example.testskotlin.service

import com.example.testskotlin.controller.model.AuthDto
import com.example.testskotlin.repository.model.Auth
import org.springframework.security.core.userdetails.UserDetails


fun Auth.toAuthDto() = AuthDto(
    password = "$passWord",
    id = "$userId"
)


