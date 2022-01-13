package gym_app

import gym.user.application.create.UserCreator
import gym.user.infrastructure.UserRepositoryMongoDB
import gym.user.infrastructure.UserRepositoryPostgreSQL
import gym_app.user.controller.model.UserRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import gym.user.infrastructure.repository.UserMongoRepository
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
    controllers = [UserControllerTest::class]
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


    @MockkBean
    lateinit var userMongoRepository: UserMongoRepository

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