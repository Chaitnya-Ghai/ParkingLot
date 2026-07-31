package chaitnya.dev.domain.lld

import chaitnya.dev.domain.models.Ticket
import chaitnya.dev.domain.models.Vehicle
import chaitnya.dev.domain.ticketGenerator.TicketService

class ParkingLotBuilding(
    private val parkingLevels: MutableList<ParkingLevel>,
    private val ticketService: TicketService
) {
    fun addLevel(level: ParkingLevel) {
        parkingLevels.add(level)
    }
    fun removeLevel(levelNumber: Int) {
        val removed = parkingLevels.removeIf { it.levelNumber == levelNumber }
        if (!removed) {
            throw NoSuchElementException("Level $levelNumber not found.")
        }
    }
    fun getList(): List<ParkingLevel> {
        return parkingLevels.toList()
    }

    fun getTicketService(): TicketService {
        return this.ticketService
    }

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