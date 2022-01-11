package gym.payment.domain.repository

import com.example.testskotlin.payment.domain.model.PayId
import com.example.testskotlin.payment.domain.model.Pays
import com.example.testskotlin.user.domain.model.UserId

interface PayRepository {

    fun save(pays:Pays)
    fun findById(payId: PayId) : Pays
    fun findByUserId(userId: UserId): List<Pays>
    fun findByPayIdAndUserId(pays: Pays): Pays
    fun findByPayIdAndUserIdOrderByPositionDesc(pays: Pays): Pays

}