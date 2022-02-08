package gym_app.user.controller


import gym.user.application.create.UserCreator
import gym.user.application.create.model.CreateUserRequest
import gym.user.application.find.UserFinder
import gym.user.domain.model.UserId
import gym.user.infrastructure.UserRepositoryMongoDB
import gym.user.infrastructure.UserRepositoryPostgreSQL
import gym_app.user.controller.model.UserRequest
import gym.user.infrastructure.controller.model.UserResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    @Autowired private var userMongoDbRepository: UserRepositoryMongoDB,
    @Autowired private var userRepositoryPostgreSQL: UserRepositoryPostgreSQL
){

    private var userCreatorMongoDB = UserCreator(userMongoDbRepository)
    private var userCreatorSQL: UserCreator =  UserCreator(userRepositoryPostgreSQL)

    @GetMapping
    fun userFinder(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
        val user = UserFinder(userRepositoryPostgreSQL).finder(UserId(userRequest.userId!!))
        return ResponseEntity(UserResponse(user.userId().value, user.name().value), HttpStatus.OK)
    }

    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest) {

        userCreatorSQL.create(CreateUserRequest(
            name = userRequest.name,
            first_lastname = userRequest.first_lastname,
            second_lastname = userRequest.second_lastname,
            email = userRequest.email,
            age = userRequest.age,
            password = userRequest.password,
            dni = userRequest.dni)
        )

        userCreatorMongoDB.create(CreateUserRequest(
            name = userRequest.name,
            first_lastname = userRequest.first_lastname,
            second_lastname = userRequest.second_lastname,
            email = userRequest.email,
            age = userRequest.age,
            password = userRequest.password,
            dni = userRequest.dni)
        )
    }

}