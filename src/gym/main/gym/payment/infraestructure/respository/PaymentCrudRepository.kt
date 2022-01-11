package gym.payment.infraestructure.respository

import com.example.testskotlin.payment.infraestructure.respository.model.PaysEntity
import org.springframework.data.repository.CrudRepository

interface PaymentCrudRepository : CrudRepository<PaysEntity, String> {

    fun findByPayId(payId: String): PaysEntity
    fun findByPayIdOrderByPosition(payId: String): PaysEntity
    fun findByPayIdAndUserIdOrderByPosition(payId: String,userId: String): PaysEntity


}