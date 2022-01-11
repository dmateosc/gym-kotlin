package gym.payment.domain.repository

import gym.payment.domain.model.PayId
import gym.payment.domain.model.Pays
import gym.user.domain.model.UserId

interface PayRepository {

    fun save(pays:Pays)
    fun findById(payId: PayId) : Pays
    fun findByUserId(userId: UserId): List<Pays>
    fun findByPayIdAndUserId(pays: Pays): Pays
    fun findByPayIdAndUserIdOrderByPositionDesc(pays: Pays): Pays

}