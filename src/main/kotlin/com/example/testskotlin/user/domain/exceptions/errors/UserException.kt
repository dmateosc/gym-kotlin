package com.example.testskotlin.user.domain.exceptions.errors

class UserException {

    class UserIsEmpty(message: String): Exception(message)

}