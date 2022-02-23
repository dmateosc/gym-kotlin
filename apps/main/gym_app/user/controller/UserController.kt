package gym_app.user.controller


import gym.user.application.find.UserResponse
import gym.user.infrastructure.bus.UserEventBus
import gym.user.infrastructure.command.create.model.UserCommandMongo
import gym.user.infrastructure.command.create.model.UserCommandPostgresSQL
import gym.user.infrastructure.query.finder.model.UserQueryMongo
import gym.user.infrastructure.query.finder.model.UserQueryPostgreSQL
import gym_app.user.controller.model.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import shared.domain.UserCreateDomainEvent
import shared.domain.bus.command.CommandBus
import shared.domain.bus.query.QueryBus
import shared.infrastructure.bus.event.rabbitmq.RabbitMqDomainEventsConsumer
import shared.infrastructure.bus.event.rabbitmq.RabbitMqPublisher
import shared.infrastructure.spring.ApiController



@RestController
class UserController(commandBus: CommandBus,queryBus: QueryBus,val userEventBus: UserEventBus,
                     val rabbitMqPublisher: RabbitMqPublisher, val rabbitMqDomainEventsConsumer: RabbitMqDomainEventsConsumer) :
    ApiController(queryBus = queryBus, commandBus = commandBus) {

    @GetMapping("/{dni}")
    fun getUser(@PathVariable dni: String): ResponseEntity<UserResponse>{
        val response = queryBus.ask(UserQueryPostgreSQL(dni = dni)) as UserResponse
        return ResponseEntity<UserResponse>(response, HttpStatus.OK)
    }


    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest): ResponseEntity<String> {

//        rabbitMqPublisher.publish(UserCreateDomainEvent(
//            name = userRequest.name!!,
//            firstLastName = userRequest.first_lastname!!,
//            secondLastName = userRequest.second_lastname!!,
//            email = userRequest.email!!,
//            age = userRequest.age!!,
//            password = userRequest.password!!,
//            dni = userRequest.dni!!),"gym")

//        rabbitMqDomainEventsConsumer.consume()

        userEventBus.publish(
            UserCreateDomainEvent(
            name = userRequest.name!!,
            firstLastName = userRequest.first_lastname!!,
            secondLastName = userRequest.second_lastname!!,
            email = userRequest.email!!,
            age = userRequest.age!!,
            password = userRequest.password!!,
            dni = userRequest.dni!!))


        return ResponseEntity<String>(HttpStatus.CREATED)

    }

    @PostMapping("/command")
    fun createUserCommand(@RequestBody userRequest: UserRequest): ResponseEntity<String> {
        dispatch(
            UserCommandMongo(
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
            UserCommandPostgresSQL(
                name = userRequest.name,
                first_lastname = userRequest.first_lastname,
                second_lastname = userRequest.second_lastname!!,
                email = userRequest.email!!,
                age = userRequest.age!!,
                password = userRequest.password!!,
                dni = userRequest.dni!!
            )
        )

        return ResponseEntity<String>(HttpStatus.CREATED)

    }

}