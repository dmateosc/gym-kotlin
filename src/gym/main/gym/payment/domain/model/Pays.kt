package gym.payment.domain.model

import gym.user.domain.model.UserId
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Pays() {
    var payId: PayId? = null
    var userId: UserId? = null
    lateinit var amount: Amount
    lateinit var initDate: Dates
    lateinit var endDate: Dates
    lateinit var position: Position

    constructor(
        userId: UserId,
        payId: PayId,
        amount: Amount,
        initDate: Dates,
        endDate: Dates,
        position: Position
    ) : this() {
        this.userId = userId
        this.payId = payId
        this.amount = amount
        this.initDate = initDate
        this.endDate = endDate
        this.position = position
    }

}

data class Position(val value: Int)

data class PayId(val value: String)

data class Amount(val value: Double)

data class Dates(val value: String) {
    private var date: LocalDate
        get() {
            return date
        }

    init {
        date = LocalDate.parse(value, DateTimeFormatter.ISO_DATE)
    }

}