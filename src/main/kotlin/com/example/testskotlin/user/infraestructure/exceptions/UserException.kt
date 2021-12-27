package com.example.testskotlin.user.infraestructure.exceptions

class UserException: Exception() {
    class UserSQLException(message: String): Exception(message)
    class UserNotFound(message: String): Exception(message)
}