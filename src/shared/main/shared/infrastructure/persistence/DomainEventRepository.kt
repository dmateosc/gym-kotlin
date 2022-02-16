package shared.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface DomainEventRepository : JpaRepository<DomainEvent, String> {

        fun findByName(name: String): List<DomainEvent>

        @Query("SELECT * FROM domain_events ORDER BY occurred_on", nativeQuery = true)
        fun findAllOrderByOccurred_on(): List<List<Any>>

    }
