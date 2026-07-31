package chaitnya.dev.parkinglot.lld

import chaitnya.dev.parkinglot.models.Ticket
import chaitnya.dev.parkinglot.pricingStrategy.CostComputation
import chaitnya.dev.payment.Payment


class ExitGate(private var costComputation: CostComputation){

    fun completeExit(
        building: ParkingLotBuilding,
        ticket: Ticket,
        payment: Payment
    ) {
        val amount = calculatePrice(ticket)

        val success: Boolean = payment.pay(amount)
        if (!success) {
            throw RuntimeException("Payment failed. Exit denied.")
        }

        building.release(ticket)
        println("Exit successful. Gate opened.")
    }

    private fun calculatePrice(ticket: Ticket): Double {
        return costComputation.compute(ticket)
    }

    fun setCurrentStrategy(costComputation: CostComputation) {
        this.costComputation = costComputation

    }
    fun getCurrentStrategy(): CostComputation {
        return this.costComputation
    }

}