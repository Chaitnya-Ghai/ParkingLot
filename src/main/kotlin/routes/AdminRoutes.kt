package chaitnya.dev.routes

import chaitnya.dev.domain.lld.ParkingLot
import chaitnya.dev.domain.pricingStrategy.VehicleCostJson
import chaitnya.dev.plugins.adminRouteValidations
import io.ktor.server.routing.*

fun Route.adminRoute(parkingLot: ParkingLot, vehicleCostJson: VehicleCostJson) {
    adminRouteValidations()
    route("/api/v1/admin") {
        levelsRoutes(parkingLot.building)
        vehicleConfigRoutes(vehicleCostJson)
    }
}
