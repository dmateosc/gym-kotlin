package com.example.testskotlin.controller

import com.example.testskotlin.controller.model.AuthDto
import com.example.testskotlin.controller.model.AuthUserDto
import com.example.testskotlin.repository.model.Auth
import com.example.testskotlin.service.AuthService

import com.example.testskotlin.service.toAuthDto
import com.example.testskotlin.controller.model.Message
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.logging.Logger
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController(value = "/")
class LoginController {

    @Autowired
    lateinit var authService: AuthService
    var logger = Logger.getLogger(LoginController::class.java.toString())

    @PostMapping("/login")
    fun login(@RequestBody authDto: AuthDto, response: HttpServletResponse): ResponseEntity<Any> {

        val auth: Auth = authService.findByNickname(authDto.nickname)

        takeIf { !(auth.comparePassword(authDto.password))}.let {
            return ResponseEntity.badRequest().body(Message("not allowd"))
        }
        val newVal = ""

        val issuer = auth.authId.toString()

        val jwt = Jwts.builder().setIssuer(issuer).setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000))
            .signWith(SignatureAlgorithm.ES256, "secret").compact()

        var cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("success"))
    }

    @PostMapping("/register")
    fun createUser(@RequestBody authUserDto: AuthUserDto) {
        authService.createUser(authUserDto)
    }


}