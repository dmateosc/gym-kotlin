package com.example.testskotlin.user.infraestructure.controller.model

data class UserRequest(
    val userId: String? = "",
    val dni: String,
    val name: String,
    val first_lastname: String,
    val second_lastname: String,
    val age: Int,
    val email: String,
    val password: String
)