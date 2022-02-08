package gym_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import shared.domain.Service
import org.springframework.boot.runApplication as runApplication


@SpringBootApplication
@ComponentScan(
    "gym.user", "gym_app", "shared",
            includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION, classes = arrayOf(Service::class))]


                )
@EnableMongoRepositories("gym.user")
@EnableJpaRepositories("gym.user")
@EntityScan("gym.user")
class Gym
fun main(args: Array<String>) {
    val runApplication = runApplication<Gym>(*args)
    run{
        runApplication

    }

}
