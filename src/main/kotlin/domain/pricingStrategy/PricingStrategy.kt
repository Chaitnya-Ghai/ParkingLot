package chaitnya.dev.domain.pricingStrategy

import chaitnya.dev.domain.models.Ticket

interface PricingStrategy {
    fun calculate(ticket: Ticket): Double
}