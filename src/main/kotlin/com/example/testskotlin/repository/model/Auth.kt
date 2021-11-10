package com.example.testskotlin.repository.model

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity
@Table(name = "Auth", schema = "public")
class Auth {

    private val passwordEncoder = BCryptPasswordEncoder()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"authId\"", nullable = false)
    var authId: Long? = null

    var nickname: String = ""
    var passWord: String = ""
    get() = field
    set(value) {
        field = this.passwordEncoder.encode(value)
    }

    @Column(name = "\"userId\"", nullable = false)
    var userId: Long? = null

    fun comparePassword(passWord: String): Boolean {
        return BCryptPasswordEncoder().matches(passWord, this.passWord)
    }
}