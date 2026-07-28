package chaitnya.dev.payment.strategy

import chaitnya.dev.payment.Payment

class Cash: Payment {
    override fun pay(amt: Double): Boolean {
        println("paying $amt via cash")
        return true
    }
}