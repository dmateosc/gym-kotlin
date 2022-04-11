package gym.payment.infraestructure.repository.model

import java.time.LocalDate

import javax.persistence.*

@Table(name = "pays")
@Entity
class PaysEntity (

    @Id
    @Column(name= "pay_id")
    val payId: String,
    @Column(name = "user_id")
    val userId: String,
    @Column
    val amount: Int,
    @Column
    val initDate: LocalDate,
    @Column
    val endDate: LocalDate,
    @Column
    val position: Int
)