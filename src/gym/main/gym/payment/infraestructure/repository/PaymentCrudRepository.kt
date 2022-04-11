package gym.payment.infraestructure.repository

import gym.payment.infraestructure.repository.model.PaysEntity
import org.springframework.data.repository.CrudRepository

interface PaymentCrudRepository : CrudRepository<PaysEntity, String> {

    fun findByPayId(payId: String): PaysEntity
    fun findByPayIdOrderByPosition(payId: String): PaysEntity
    fun findByPayIdAndUserIdOrderByPosition(payId: String,userId: String): PaysEntity


}