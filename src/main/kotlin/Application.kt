package chaitnya.dev

import chaitnya.dev.parkinglot.lld.EntryGate
import chaitnya.dev.parkinglot.lld.ExitGate
import chaitnya.dev.parkinglot.lld.ParkingLot
import chaitnya.dev.parkinglot.lld.ParkingLotBuilding
import chaitnya.dev.parkinglot.pricingStrategy.CostComputation
import chaitnya.dev.parkinglot.pricingStrategy.FixedHours
import chaitnya.dev.parkinglot.pricingStrategy.VehicleCostJson
import chaitnya.dev.parkinglot.ticketGenerator.TicketService
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
