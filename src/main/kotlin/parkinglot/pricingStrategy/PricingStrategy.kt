package chaitnya.dev.parkinglot.pricingStrategy

import chaitnya.dev.parkinglot.models.Ticket

interface PricingStrategy {
    fun calculate(ticket: Ticket): Double
}