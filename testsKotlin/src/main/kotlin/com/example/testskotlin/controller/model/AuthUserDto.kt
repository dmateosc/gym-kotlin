package com.example.testskotlin.controller.model

data class AuthUserDto(

    val password: String? = null,
    val nickname: String? = null,
    val user: UserDto? = null

)