package com.example.testskotlin.controller

import com.example.testskotlin.repository.model.User
import com.example.testskotlin.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController(value = "/")
class Controller {

    @Autowired
    lateinit var userService: UserService
    var logger = Logger.getLogger(Controller::class.java.toString())

    @GetMapping
    fun solveFunction(){

        val listBoolean = listOf<Boolean>(true, false)
        var nombre = "false"
        listBoolean.takeIf { list -> list.last()  }?.apply { nombre = "Ultima condición true" }
        listBoolean.takeIf { list -> list.first()  }?.apply { nombre = "Primera condición false" }

        listBoolean.takeIf { list -> list.first() || list.last() }?.apply { nombre = "Una de las condiciones OK" }

        print(nombre)
    }

    @GetMapping("/user")
    fun getUser() {
        var user : User = userService.getUsers()
        logger.info(user.toString())
    }

}