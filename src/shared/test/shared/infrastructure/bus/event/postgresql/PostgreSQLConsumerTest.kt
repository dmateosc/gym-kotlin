package shared.infrastructure.bus.event.postgresql

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import shared.domain.DomainEvent
import shared.domain.UserCreateDomainEvent
import shared.infrastructure.bus.event.DomainEventsInformation
import shared.infrastructure.persistence.DomainEventRepository
import java.sql.Timestamp
import java.time.Instant
import java.util.*

@ExtendWith(MockKExtension::class)
internal class PostgreSQLConsumerTest {

    private lateinit var domainEventRepository: DomainEventRepository

    @MockkBean
    private lateinit var postgreSQLConsumer: PostgreSQLConsumer

    @MockkBean
    private var domainEventsInformation: DomainEventsInformation = mockk(relaxed = true)

    @BeforeEach
    fun setUp(){
        this.domainEventRepository = mockk()
        this.postgreSQLConsumer = PostgreSQLConsumer(domainEventRepository,
            mockk(), domainEventsInformation
                                                    )
    }


    @Test
    fun `should be read a list`(){


//        every { domainEventRepository.findAllOrderByOccurred_on() } returns listOf<List<Any>>(
//            listOf<Any>("eventName","eventName","eventName","eventName",Timestamp.from(Instant.now())),
//            listOf<Any>("eventName","eventName","eventName","eventName",Timestamp.from(Instant.now())),
//            listOf<Any>("eventName","eventName","eventName","eventName",Timestamp.from(Instant.now())),
//                                                                                                )
//        every { domainEventsInformation.forName("eventName")} answers { DomainEvent::class.java.kotlin}
//
//        every { mockk<DomainEvent>().javaClass.getConstructor().newInstance()} returns UserCreateDomainEvent().javaClass
//            .getConstructor().newInstance()
//        postgreSQLConsumer.consume()
//

    }



}