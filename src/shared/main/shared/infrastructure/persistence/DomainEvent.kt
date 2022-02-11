package shared.infrastructure.persistence

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "domain_events")
@Entity
data class DomainEvent( @Id
                   @Column(name= "id")
                   val id: String,
                   @Column(name= "aggregate_id")
                   val aggregateId: String,
                   @Column(name= "name")
                   val name: String,
                   @Column(name = "body")
                   val body: Serializable,
                   @Column(name= "occurred_on")
                   val occurred_on: String,
                   ) {
}