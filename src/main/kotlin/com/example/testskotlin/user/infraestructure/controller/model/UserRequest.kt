package com.example.testskotlin.user.infraestructure.controller.model

data class UserRequest(
    val userId: String,
    val name: String,
    val lastName: String,
    val email: String,
    val password: String
)