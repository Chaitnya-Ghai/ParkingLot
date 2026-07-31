package chaitnya.dev.domain.payment

interface Payment {
    fun pay(amt: Double): Boolean
}