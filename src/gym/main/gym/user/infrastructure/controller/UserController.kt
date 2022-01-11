package gym.user.infrastructure.controller

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