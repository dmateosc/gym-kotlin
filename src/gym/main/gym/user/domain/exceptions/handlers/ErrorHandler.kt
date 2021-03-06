package gym.user.domain.exceptions.handlers

import gym.user.domain.exceptions.errors.UserException
import org.springframework.web.bind.annotation.ExceptionHandler

class ErrorHandler {

    @ExceptionHandler(UserException.UserIsEmpty::class)
    fun takeGenericException(e: UserException.UserIsEmpty){
        throw Exception(e)
    }

}