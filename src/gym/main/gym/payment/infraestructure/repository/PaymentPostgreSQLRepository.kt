package gym.payment.infraestructure.repository

import gym.payment.domain.model.PayId
import gym.payment.domain.model.Pays
import gym.payment.domain.repository.PayRepository
import gym.user.domain.model.UserId
import org.springframework.stereotype.Repository

@Repository
class PaymentPostgreSQLRepository (private val paymentCrudRepository: PaymentCrudRepository): PayRepository {
    override fun save(pays: Pays) {
        TODO("Not yet implemented")
    }

    override fun findById(payId: PayId): Pays {
        TODO("Not yet implemented")
    }

    override fun findByUserId(userId: UserId): List<Pays> {
        TODO("Not yet implemented")
    }

    override fun findByPayIdAndUserId(pays: Pays): Pays {
        TODO("Not yet implemented")
    }

    override fun findByPayIdAndUserIdOrderByPositionDesc(pays: Pays): Pays {
        TODO("Not yet implemented")
    }
}