package chaitnya.dev.parkinglot.lld

import chaitnya.dev.parkinglot.models.Ticket
import chaitnya.dev.parkinglot.models.Vehicle
import chaitnya.dev.parkinglot.ticketGenerator.TicketService

class ParkingLotBuilding(
    private val parkingLevels: MutableList<ParkingLevel>,
    private val ticketService: TicketService
) {
    fun allocate(vehicle: Vehicle): Ticket {
        for (level in parkingLevels) {
            if (level.hasAvailability(vehicle.vehicleType)) {
                val spot = level.park(vehicle.vehicleType)
                return ticketService.createTicket(
                    vehicle,
                    level,
                    spot
                )
            }
        }
        throw IllegalStateException("Parking Full")
    }

    fun release(ticket: Ticket) {
        val level = parkingLevels.firstOrNull {
            it.levelNumber == ticket.level
        } ?: throw IllegalArgumentException(
            "Invalid parking level ${ticket.level}"
        )

        level.unPark(
            ticket.vehicle.vehicleType,
            ticket.parkingSpot
        )
    }
}