package gym_app.user.controller


import gym.user.infrastructure.bus.UserEventBus
import gym.user.infrastructure.command.create.model.UserCommandMongo
import gym.user.infrastructure.command.create.model.UserCommandPostgresSQL
import gym_app.user.controller.model.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import shared.domain.UserCreateDomainEvent
import shared.domain.bus.command.CommandBus
import shared.domain.bus.query.QueryBus
import shared.infrastructure.spring.ApiController



@RestController
class UserController(commandBus: CommandBus,queryBus: QueryBus,val userEventBus: UserEventBus) :
    ApiController(queryBus = queryBus, commandBus = commandBus) {

    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest): ResponseEntity<String> {

        userEventBus.publish(
            UserCreateDomainEvent(
            name = userRequest.name!!,
            firstLastName = userRequest.first_lastname!!,
            secondLastName = userRequest.second_lastname!!,
            email = userRequest.email!!,
            age = userRequest.age!!,
            password = userRequest.password!!,
            dni = userRequest.dni!!))
//        dispatch(
//            UserCommandMongo(
//                name = userRequest.name!!,
//                first_lastname = userRequest.first_lastname!!,
//                second_lastname = userRequest.second_lastname!!,
//                email = userRequest.email!!,
//                age = userRequest.age!!,
//                password = userRequest.password!!,
//                dni = userRequest.dni!!
//                             )
//                )
//        dispatch(
//            UserCommandPostgresSQL(
//                name = userRequest.name,
//                first_lastname = userRequest.first_lastname,
//                second_lastname = userRequest.second_lastname!!,
//                email = userRequest.email!!,
//                age = userRequest.age!!,
//                password = userRequest.password!!,
//                dni = userRequest.dni!!
//            )
//        )

        return ResponseEntity<String>(HttpStatus.CREATED)

    }

}