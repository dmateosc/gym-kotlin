package shared.infrastructure.config

import io.github.cdimascio.dotenv.Dotenv
import shared.domain.Service

@Service
class Parameter {
    private var dotenv: Dotenv

    constructor(dotenv: Dotenv) {
        this.dotenv = dotenv
    }

    @Throws(ParameterNotExist::class)
    operator fun get(key: String): String {
        return dotenv[key] ?: throw ParameterNotExist(key)
    }

    @Throws(ParameterNotExist::class)
    fun getInt(key: String): Int {
        val value = get(key)
        return value.toInt()
    }

}