package chaitnya.dev.domain.payment.strategy

import chaitnya.dev.domain.payment.Payment

class Upi: Payment {
    override fun pay(amt: Double): Boolean {
        println("paying $amt via upi")
        return true
    }
}