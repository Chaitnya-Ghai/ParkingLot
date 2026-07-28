package chaitnya.dev.parkinglot.pricingStrategy

import chaitnya.dev.parkinglot.models.Ticket
import chaitnya.dev.parkinglot.models.VehicleType
import kotlin.time.Clock

class FixedHours(val vehicleCostJson: VehicleCostJson) : PricingStrategy {
    override fun calculate(ticket: Ticket): Double {
        val totalTime = Clock.System.now() - ticket.entryTime
        val totalCost =totalTime.inWholeHours * vehicleCostJson.perHour(ticket.vehicle.vehicleType) * (18 / 100)
        return totalCost
    }
}

object VehicleCostJson {
    val map = HashMap<String, Double>()
    fun add(vehicleType: String, costPerHour: Double) {
        map[vehicleType] = costPerHour
    }
    fun perHour(vehicleType: VehicleType): Double {
        return map[vehicleType.toString()] ?: 20.0
    }
}