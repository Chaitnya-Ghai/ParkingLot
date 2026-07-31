package chaitnya.dev.plugins

import chaitnya.dev.domain.lld.ParkingLot
import chaitnya.dev.domain.pricingStrategy.VehicleCostJson
import chaitnya.dev.routes.adminRoute
import chaitnya.dev.routes.userRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configAppRouting(parkingLot: ParkingLot, vehicleCostJson: VehicleCostJson) {
    routing {
        // admin-route
        adminRoute(parkingLot, vehicleCostJson)

        // user-route (vehicle entry/exit)
        userRoute(parkingLot)

    }
}
