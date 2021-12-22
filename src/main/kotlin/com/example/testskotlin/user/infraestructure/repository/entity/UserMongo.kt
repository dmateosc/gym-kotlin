package com.example.testskotlin.user.infraestructure.repository.entity

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id
@Document("gym_users")
data class UserMongo(

    @Id
    val userId: String,
    val dni: String,
    val name: String,
    val first_lastname: String,
    val second_lastname: String,
    val email: String,
    val age: Int,
    val password: String


)