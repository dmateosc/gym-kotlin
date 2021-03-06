package gym.user.infrastructure.errorhandler

import gym.user.infrastructure.exceptions.UserException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(Exception::class)
    fun takeGenericException(e: Exception): ResponseEntity<ErrorResponse>{
        return ResponseEntity(
            ErrorResponse(e.message!!,"exception"),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(UserException.UserSQLException::class)
    fun sqlException(e: UserException.UserSQLException): ResponseEntity<ErrorResponse>{
        return ResponseEntity(
            ErrorResponse(e.message!!,"exception"),
            HttpStatus.BAD_REQUEST
        )
    }

}