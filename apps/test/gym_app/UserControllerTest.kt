package gym_app

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan

@ExtendWith(MockKExtension::class)
@AutoConfigureMockMvc
@SpringBootTest
@ComponentScan("gym.user")
internal class UserControllerTest {

//    @Autowired
//    lateinit var mockMvc: MockMvc
//
//
//    private lateinit var userCreatorMongoDB: UserCreator
//    private lateinit var userCreatorSql: UserCreator
//    private lateinit var repositorySql: UserRepositoryPostgreSQL

//    @BeforeEach
//    private fun setUp() {
//        val repositoryMongo = mockk<UserRepositoryMongoDB>()
//        repositorySql = mockk()
//        userCreatorSql = UserCreator(repositorySql)
//        userCreatorMongoDB = UserCreator(repositoryMongo)
//    }

//    @Test
//    fun `should be ok`() {
//
//        val mockUUID = mockk<UUID>()
//
//        every { mockUUID.randomUUID() } returns "5c8126d3-32ac-4d7e-b2cb-e4d71b3cb3e0"
//
//        every {
//            userCreatorMongoDB.create(
//                CreateUserRequest(
//                    name = "prueba",
//                    password = "prueba",
//                    email = "prueba",
//                    dni = "28968666P",
//                    age = 1,
//                    second_lastname = "prueba",
//                    first_lastname = "prueba"
//                                 )
//                                     )
//        } returns Unit
//
//
//        mockMvc.post("/") {
//            contentType = MediaType.APPLICATION_JSON
//            content = ObjectMapper().writeValueAsString(
//                UserRequest(
//                    name = "prueba",
//                    password = "prueba",
//                    email = "prueba",
//                    dni = "28968666P",
//                    age = 1,
//                    second_lastname = "prueba",
//                    first_lastname = "prueba"
//                           )
//                                                       )
//            accept = MediaType.APPLICATION_JSON
//        }
//            .andExpect {
//                status { isOk() }
//            }
//    }

//
//    @Test
//    fun `should be 400`() {
//
//        mockMvc.post("/") {
//            contentType = MediaType.APPLICATION_JSON
//            content = ObjectMapper().writeValueAsString(
//                UserRequest(
//                    name = "prueba",
//                    first_lastname = "prueba",
//                    password = "prueba",
//                    email = "prueba",
//                    dni = "prueba",
//                    age = 1,
//                    second_lastname = "prueba"
//                           )
//                                                       )
//            accept = MediaType.APPLICATION_JSON
//        }.andExpect {
//            status { isBadRequest() }
//        }
//
//
//    }


}