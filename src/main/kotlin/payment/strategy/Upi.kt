package chaitnya.dev.payment.strategy

import chaitnya.dev.payment.Payment

class Upi: Payment {
    override fun pay(amt: Double): Boolean {
        println("paying $amt via upi")
        return true
    }
}