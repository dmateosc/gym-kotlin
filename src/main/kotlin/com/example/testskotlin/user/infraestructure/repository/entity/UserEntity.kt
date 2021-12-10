package com.example.testskotlin.user.infraestructure.repository.entity


import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "user")
@Entity
class UserEntity (

    @Id
    @Column
    val userId: String,
    @Column
    val dni: String,
    @Column
    val name: String,
    @Column
    val first_lastname: String,
    @Column
    val second_lastname: String,
    @Column
    val email: String,
    @Column
    val age: Int,
    @Column
    val password: String


)