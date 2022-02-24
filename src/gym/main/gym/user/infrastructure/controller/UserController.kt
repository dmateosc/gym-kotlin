package gym.user.infrastructure.controller
//
//import gym.user.application.create.UserCreator
//import gym.user.application.find.UserFinder
//import gym.user.domain.model.UserId
//import gym.user.infrastructure.repository.UserRepositoryMongoDB
//import gym.user.infrastructure.repository.UserRepositoryPostgreSQL
//import gym.user.infrastructure.controller.model.UserRequest
//import gym.user.infrastructure.controller.model.UserResponse
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//class UserController(
//    @Autowired private var userMongoDbRepository: UserRepositoryMongoDB,
//    @Autowired private var userRepositoryPostgreSQL: UserRepositoryPostgreSQL
//){
//
//    @Autowired private var userCreatorMongoDB = UserCreator(userRepositoryPostgreSQL)
//    @Autowired private var userCreatorSQL: UserCreator =  UserCreator(userMongoDbRepository)
//
//    @GetMapping
//    fun userFinder(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
//        val user = UserFinder(userRepositoryPostgreSQL).finder(UserId(userRequest.userId!!))
//        return ResponseEntity(UserResponse(user.userId().value, user.name().value), HttpStatus.OK);
//    }
//
//    @PostMapping
//    fun createUser(@RequestBody userRequest: UserRequest) {
//
//        userCreatorSQL.create(
//            userRequest.name,
//            userRequest.first_lastname,
//            userRequest.second_lastname,
//            userRequest.email,
//            userRequest.age,
//            userRequest.password,
//            userRequest.dni
//        )
//
//        userCreatorMongoDB.create(
//            userRequest.name,
//            userRequest.first_lastname,
//            userRequest.second_lastname,
//            userRequest.email,
//            userRequest.age,
//            userRequest.password,
//            userRequest.dni
//        )
//    }
//
//}