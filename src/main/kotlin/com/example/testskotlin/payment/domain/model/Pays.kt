package com.example.testskotlin.payment.domain.model

import com.example.testskotlin.user.domain.model.UserId
import org.springframework.format.datetime.DateFormatter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Pays() {
    var payId: PayId? = null
    var userId: UserId? = null
    lateinit var amount: Amount
    lateinit var initDate: Dates
    lateinit var endDate: Dates

    constructor(
        amount: Amount,
        initDate: Dates,
        endDate: Dates
    ) : this() {
        this.amount = amount
        this.initDate = initDate
        this.endDate = endDate
    }

    constructor(
        userId: UserId,
        amount: Amount,
        initDate: Dates,
        endDate: Dates
    ) : this() {
        this.userId = userId
        this.amount = amount
        this.initDate = initDate
        this.endDate = endDate
    }

    constructor(
        userId: UserId,
        payId: PayId,
        amount: Amount,
        initDate: Dates,
        endDate: Dates
    ) : this() {
        this.userId = userId
        this.payId = payId
        this.amount = amount
        this.initDate = initDate
        this.endDate = endDate
    }

    constructor(
        payId: PayId,
        amount: Amount,
        initDate: Dates,
        endDate: Dates
    ) : this() {
        this.payId = payId
        this.amount = amount
        this.initDate = initDate
        this.endDate = endDate
    }

}

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