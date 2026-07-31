package chaitnya.dev.parkinglot.pricingStrategy

import chaitnya.dev.parkinglot.models.Ticket
import chaitnya.dev.parkinglot.models.VehicleType
import kotlin.time.Clock

class FixedHours(val vehicleCostJson: VehicleCostJson) : PricingStrategy {
    override fun calculate(ticket: Ticket): Double {
        val totalTime = Clock.System.now() - ticket.entryTime
        val cost = totalTime.inWholeHours * vehicleCostJson.perHour(ticket.vehicle.vehicleType)
        val gst = cost * 0.18
        return cost+gst
    }
}

class FixedDay(val vehicleCostJson: VehicleCostJson) : PricingStrategy {
    override fun calculate(ticket: Ticket): Double {
        val cost = vehicleCostJson.map[ticket.vehicle.vehicleType]
        val gst: Double? = cost?.times(0.18)
        return gst?.let { cost.plus(it) } ?: throw NoSuchElementException()
    }
}

class VehicleCostJson {
    val map = HashMap<VehicleType, Double>()
    fun add(vehicleType: VehicleType, costPerHour: Double) {
        map[vehicleType] = costPerHour
    }
    fun perHour(vehicleType: VehicleType): Double {
        return map.getValue(vehicleType)
    }
}