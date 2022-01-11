package gym_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication as runApplication


@SpringBootApplication
class Gym

fun main(args: Array<String>) {
    val runApplication = runApplication<Gym>(*args)
    run{
        runApplication

    }

}
