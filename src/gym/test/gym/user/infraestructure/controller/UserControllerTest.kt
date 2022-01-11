package gym.gym.user.infraestructure.controller

import com.example.testskotlin.user.application.create.UserCreator
import com.example.testskotlin.user.infraestructure.UserRepositoryMongoDB
import com.example.testskotlin.user.infraestructure.UserRepositoryPostgreSQL
import com.example.testskotlin.user.infraestructure.controller.model.UserRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import gym.user.infraestructure.controller.UserController
import io.mockk.*
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@ExtendWith(MockKExtension::class)
@WebMvcTest(
    controllers = [UserController::class]
)
internal class UserControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var userRepositorySql: UserRepositoryPostgreSQL

    @MockkBean
    lateinit var userRepositoryMongoDB: UserRepositoryMongoDB

    @MockkBean
    lateinit var userCreatorMongoDB: UserCreator



    @Test
    fun `should be ok`() {

        every {
            userCreatorMongoDB.create(
                name = "prueba",
                password = "prueba",
                email = "prueba",
                dni = "28968669P",
                age = 1,
                secondLastName = "prueba",
                firstLastName = "prueba"
            )
        } returns Unit


        mockMvc.post("/") {
            contentType = MediaType.APPLICATION_JSON
            content = ObjectMapper().writeValueAsString(
                UserRequest(
                    name = "prueba",
                    password = "prueba",
                    email = "prueba",
                    dni = "28968669P",
                    age = 1,
                    second_lastname = "prueba",
                    first_lastname = "prueba"
                )
            )
            accept = MediaType.APPLICATION_JSON
        }
            .andExpect {
                status { isOk() }
            }
    }


    @Test
    fun `should be 400`() {

        mockMvc.post("/") {
            contentType = MediaType.APPLICATION_JSON
            content = ObjectMapper().writeValueAsString(
                UserRequest(
                    name = "prueba",
                    first_lastname = "prueba",
                    password = "prueba",
                    email = "prueba",
                    dni = "prueba",
                    age = 1,
                    second_lastname = "prueba"
                )
            )
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest() }
        }


    }


}