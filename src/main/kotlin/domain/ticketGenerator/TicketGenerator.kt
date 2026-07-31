package chaitnya.dev.domain.ticketGenerator

import chaitnya.dev.domain.lld.ParkingLevel
import chaitnya.dev.domain.models.ParkingSpot
import chaitnya.dev.domain.models.Ticket
import chaitnya.dev.domain.models.Vehicle

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