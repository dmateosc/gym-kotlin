package shared.infrastructure.config

class ParameterNotExist(key: String?) :
        Throwable(String.format("The parameter <%s> does not exist in the environment file", key)) {


}