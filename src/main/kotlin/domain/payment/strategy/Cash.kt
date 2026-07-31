package chaitnya.dev.domain.payment.strategy

import chaitnya.dev.domain.payment.Payment

class Cash: Payment {
    override fun pay(amt: Double): Boolean {
        println("paying $amt via cash")
        return true
    }
}