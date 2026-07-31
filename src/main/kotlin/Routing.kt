package chaitnya.dev

import chaitnya.dev.dtos.ExitRequest
import chaitnya.dev.dtos.LevelDto
import chaitnya.dev.dtos.toDomain
import chaitnya.dev.parkinglot.lld.ParkingLot
import chaitnya.dev.parkinglot.lld.ParkingLotBuilding
import chaitnya.dev.parkinglot.models.Vehicle
import chaitnya.dev.parkinglot.models.VehicleType
import chaitnya.dev.parkinglot.pricingStrategy.VehicleCostJson
import chaitnya.dev.payment.Payment
import chaitnya.dev.payment.strategy.Cash
import chaitnya.dev.payment.strategy.Upi
import chaitnya.dev.plugins.adminRouteValidations
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Application.configAppRouting(parkingLot: ParkingLot, vehicleCostJson: VehicleCostJson) {
    routing {
        // admin-route
        adminRoute(parkingLot, vehicleCostJson)

        // else-routes

    }
}

fun Route.adminRoute(parkingLot: ParkingLot , vehicleCostJson: VehicleCostJson) {
    adminRouteValidations()
    route("/api/v1/admin") {
        levelsRoutes(parkingLot.building)
        vehicleRoutes(parkingLot,vehicleCostJson)

    }
}
fun Route.levelsRoutes(building: ParkingLotBuilding) {
    post("levels") {
        val req = call.receive<LevelDto>()
        building.addLevel(req.toDomain())
        call.respond(HttpStatusCode.Created, "$req has been successfully created")
    }
    get("/levels") {
        val response = building.getList().map { it.toResponse() }
        return@get call.respond(HttpStatusCode.OK, response)
    }
    delete("/levels/{id}") {
        val levelId = call.parameters["id"]?.toIntOrNull()
            ?: return@delete call.respond(
                HttpStatusCode.BadRequest,
                "Invalid level id"
            )
        building.removeLevel(levelId)
        call.respond(HttpStatusCode.OK, "level $levelId deleted")
    }
}

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