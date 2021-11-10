package com.example.testskotlin.repository.model


import javax.persistence.*

@Entity
@Table(name = "User", schema = "public" )
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"userId\"", nullable = false)
    var userId: Long? = null

    @Column(name = "name")
    var name: String? = null
    var lastname: String? = null
    var email: String? = null

    var dni: String? = null
    var age: Number? = null
    var photo: String? = null





}
