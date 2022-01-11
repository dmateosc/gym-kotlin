package gym.user.infrastructure.exceptions

class UserException: Exception() {
    class UserSQLException(message: String): Exception(message)
    class UserNotFound(message: String): Exception(message)
}