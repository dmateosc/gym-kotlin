package com.example.testskotlin.controller.model


data class AuthDto(
    val nickname: String,
    val password: String,
    val id: String? = null,
    val access_token: String? = null,
    val refresh_token: String? = null
)