package chaitnya.dev.routes

import chaitnya.dev.dtos.ExitRequest
import chaitnya.dev.domain.lld.ParkingLot
import chaitnya.dev.domain.models.Vehicle
import chaitnya.dev.domain.models.VehicleType
import chaitnya.dev.domain.payment.Payment
import chaitnya.dev.domain.payment.strategy.Cash
import chaitnya.dev.domain.payment.strategy.Upi
import chaitnya.dev.domain.pricingStrategy.VehicleCostJson
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.vehicleRoutes(parkingLot: ParkingLot, vehicleCostJson: VehicleCostJson) {
    route("/vehicles") {

        post("/config") {
            val req = call.receive<Map<VehicleType, Double>>()
            req.forEach { (vehicleType, costPerHour) ->
                vehicleCostJson.add(vehicleType, costPerHour)
            }
            call.respond(HttpStatusCode.OK)
        }

        get("/config") {
            call.respond(vehicleCostJson.map.toMap())
        }
        post("/entry") {
            val vehicle = call.receive<Vehicle>()
            val ticket = parkingLot.vehicleArrives(vehicle)
            call.respond(HttpStatusCode.Created, ticket)
        }

        post("/exit") {
            val req = call.receive<ExitRequest>()
            val ticket = parkingLot.building.getTicketService().getTicket(req.ticketId)
                ?: return@post call.respond(HttpStatusCode.NotFound, "Ticket ${req.ticketId} not found")

            val payment: Payment = when (req.paymentMode.lowercase()) {
                "cash" -> Cash()
                "upi" -> Upi()
                else -> return@post call.respond(HttpStatusCode.BadRequest, "Unknown payment mode")
            }
            parkingLot.vehicleExits(ticket, payment)
            call.respond(HttpStatusCode.OK)
        }

    }
}
