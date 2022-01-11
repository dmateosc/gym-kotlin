package gym_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.boot.runApplication as runApplication


@SpringBootApplication
@ComponentScan(
    "gym.user", "gym_app"   )
class Gym
fun main(args: Array<String>) {
    val runApplication = runApplication<Gym>(*args)
    run{
        runApplication

    }

}
