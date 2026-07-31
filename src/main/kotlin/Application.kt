package chaitnya.dev

import chaitnya.dev.domain.lld.EntryGate
import chaitnya.dev.domain.lld.ExitGate
import chaitnya.dev.domain.lld.ParkingLot
import chaitnya.dev.domain.lld.ParkingLotBuilding
import chaitnya.dev.domain.pricingStrategy.CostComputation
import chaitnya.dev.domain.pricingStrategy.FixedHours
import chaitnya.dev.domain.pricingStrategy.VehicleCostJson
import chaitnya.dev.domain.ticketGenerator.TicketService
import chaitnya.dev.plugins.configAppRouting
import chaitnya.dev.plugins.configureSerialization
import chaitnya.dev.plugins.configureStatusPages
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    val building = ParkingLotBuilding(
        parkingLevels = mutableListOf(),
        ticketService = TicketService()
    )
    val vehicleCostJson = VehicleCostJson()
    val entryGate : EntryGate = EntryGate
    val exitGate: ExitGate = ExitGate(CostComputation(FixedHours(vehicleCostJson)))
    val parkingLot = ParkingLot(building,entryGate,exitGate)
    configureStatusPages()
    configAppRouting(parkingLot, vehicleCostJson)

}
