package chaitnya.dev.parkinglot.ticketGenerator

import chaitnya.dev.parkinglot.lld.ParkingLevel
import chaitnya.dev.parkinglot.models.ParkingSpot
import chaitnya.dev.parkinglot.models.Ticket
import chaitnya.dev.parkinglot.models.Vehicle

class TicketService {
    private val issuedTickets = mutableMapOf<String, Ticket>()
    fun createTicket(vehicle: Vehicle, level: ParkingLevel, spot: ParkingSpot): Ticket {
        val ticket = Ticket(
            vehicle = vehicle,
            level = level.levelNumber,
            parkingSpot = spot
        )
        issuedTickets[ticket.ticketId]=ticket
        return ticket
    }

    fun getTicket(id: String): Ticket? {
        return issuedTickets[id]
    }

}