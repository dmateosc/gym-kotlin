package com.example.testskotlin.user.domain.exceptions.handlers

import com.example.testskotlin.user.domain.exceptions.errors.UserException
import com.example.testskotlin.user.infraestructure.errorhandler.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

class ErrorHandler {

    @ExceptionHandler(UserException.UserIsEmpty::class)
    fun takeGenericException(e: UserException.UserIsEmpty){
        throw Exception(e)
    }

}