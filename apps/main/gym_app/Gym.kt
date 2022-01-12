package gym_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.boot.runApplication as runApplication


@SpringBootApplication
@ComponentScan(
    "gym.user.*", "gym_app"
              )
@EnableMongoRepositories("gym.user")
class Gym
fun main(args: Array<String>) {
    val runApplication = runApplication<Gym>(*args)
    run{
        runApplication

    }

}
