package chaitnya.dev.routes

import chaitnya.dev.dtos.ExitRequest
import chaitnya.dev.domain.lld.ParkingLot
import chaitnya.dev.domain.models.Vehicle
import chaitnya.dev.domain.payment.Payment
import chaitnya.dev.domain.payment.strategy.Cash
import chaitnya.dev.domain.payment.strategy.Upi
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.userRoute(parkingLot: ParkingLot) {
    route("/api/v1/vehicles") {
        get("/availability") {
            call.respond(parkingLot.building.freeSpotByLevel())
        }

        get("/availability/{levelId}") {
            val levelId = call.parameters["levelId"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid level id")
            call.respond(parkingLot.building.freeSpotCount(levelId))
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
