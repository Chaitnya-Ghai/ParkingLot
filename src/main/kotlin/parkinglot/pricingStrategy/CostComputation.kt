package chaitnya.dev.parkinglot.pricingStrategy

import chaitnya.dev.parkinglot.models.Ticket

class CostComputation(
    val strategy: PricingStrategy
) {
    fun compute(ticket: Ticket): Double {
        return strategy.calculate(ticket)
    }
}