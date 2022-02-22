package gym_app.user.controller


import gym.user.infrastructure.command.create.model.CreateUserCommandMongo
import gym.user.infrastructure.command.create.model.CreateUserCommandPostgresSQL
import gym_app.user.controller.model.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import shared.domain.bus.command.CommandBus
import shared.domain.bus.query.QueryBus
import shared.infrastructure.spring.ApiController



@RestController
class UserController(commandBus: CommandBus,queryBus: QueryBus) :
    ApiController(queryBus = queryBus, commandBus = commandBus) {


//    private var userCreatorMongoDB = UserCreator(userMongoDbRepository)
//    private var userCreatorSQL: UserCreator =  UserCreator(userRepositoryPostgreSQL)

//    @GetMapping
//    fun userFinder(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
//        val user = UserFinder(userRepositoryPostgreSQL).finder(UserId(userRequest.userId!!))
//        return ResponseEntity(UserResponse(user.userId().value, user.name().value), HttpStatus.OK)
//    }

    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest): ResponseEntity<String> {
        dispatch(
            CreateUserCommandMongo(
                name = userRequest.name!!,
                first_lastname = userRequest.first_lastname!!,
                second_lastname = userRequest.second_lastname!!,
                email = userRequest.email!!,
                age = userRequest.age!!,
                password = userRequest.password!!,
                dni = userRequest.dni!!
                             )
                )

        dispatch(
            CreateUserCommandPostgresSQL(
                name = userRequest.name!!,
                first_lastname = userRequest.first_lastname!!,
                second_lastname = userRequest.second_lastname!!,
                email = userRequest.email!!,
                age = userRequest.age!!,
                password = userRequest.password!!,
                dni = userRequest.dni!!
            )
        )


//        userCreatorSQL.create(CreateUserRequest(
//            name = userRequest.name!!,
//            first_lastname = userRequest.first_lastname!!,
//            second_lastname = userRequest.second_lastname!!,
//            email = userRequest.email!!,
//            age = userRequest.age!!,
//            password = userRequest.password!!,
//            dni = userRequest.dni!!)
//        )
//
//        userCreatorMongoDB.create(CreateUserRequest(
//            name = userRequest.name,
//            first_lastname = userRequest.first_lastname,
//            second_lastname = userRequest.second_lastname,
//            email = userRequest.email,
//            age = userRequest.age,
//            password = userRequest.password,
//            dni = userRequest.dni)
//        )
        return ResponseEntity<String>(HttpStatus.CREATED)

    }

}