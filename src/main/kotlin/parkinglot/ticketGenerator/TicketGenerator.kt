package chaitnya.dev.parkinglot.ticketGenerator

import chaitnya.dev.parkinglot.lld.ParkingLevel
import chaitnya.dev.parkinglot.models.ParkingSpot
import chaitnya.dev.parkinglot.models.Ticket
import chaitnya.dev.parkinglot.models.Vehicle

class TicketService {

    fun createTicket(vehicle: Vehicle, level: ParkingLevel, spot: ParkingSpot): Ticket =
        Ticket(
            vehicle = vehicle,
            level = level.levelNumber,
            parkingSpot = spot
        )
}