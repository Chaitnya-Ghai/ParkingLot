package chaitnya.dev.domain.pricingStrategy

import chaitnya.dev.domain.models.Ticket

class CostComputation(
    val strategy: PricingStrategy
) {
    fun compute(ticket: Ticket): Double {
        return strategy.calculate(ticket)
    }
}