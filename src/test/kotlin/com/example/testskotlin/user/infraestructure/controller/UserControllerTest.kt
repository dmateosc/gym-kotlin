package com.example.testskotlin.user.infraestructure.controller

import com.example.testskotlin.user.application.create.UserCreator
import com.example.testskotlin.user.domain.repository.UserRepository
import com.example.testskotlin.user.infraestructure.UserRepositoryPostgreSQL
import com.example.testskotlin.user.infraestructure.controller.model.UserRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(
    controllers = [UserController::class]
)
internal class UserControllerTest{




    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var userRepository: UserRepositoryPostgreSQL


    lateinit var userCreator: UserCreator

    @BeforeEach
    fun setUp(){
        userCreator = UserCreator(userRepository)
    }


    @Test
    fun `should be 400`(){

        mockMvc.post("/"){
            contentType = MediaType.APPLICATION_JSON
            content = ObjectMapper().writeValueAsString(UserRequest(
                name = "prueba",
                password = "prueba",
                email = "prueba",
                dni = "prueba",
                age = 1,
                second_lastname = "prueba",
                first_lastname = "prueba"
            ))
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest() }
        }




    }



}