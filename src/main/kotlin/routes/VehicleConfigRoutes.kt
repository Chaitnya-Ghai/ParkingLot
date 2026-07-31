package chaitnya.dev.routes

import chaitnya.dev.domain.models.VehicleType
import chaitnya.dev.domain.pricingStrategy.VehicleCostJson
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.vehicleConfigRoutes(vehicleCostJson: VehicleCostJson) {
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
    }
}
