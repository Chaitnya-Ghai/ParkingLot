package chaitnya.dev.payment

interface Payment {
    fun pay(amt: Double): Boolean
}