package chaitnya.dev.parkinglot.lld

import chaitnya.dev.parkinglot.models.Ticket
import chaitnya.dev.parkinglot.models.Vehicle
import chaitnya.dev.parkinglot.pricingStrategy.CostComputation
import chaitnya.dev.payment.Payment

class ParkingLot(
    val building: ParkingLotBuilding,
    val entranceGate: EntryGate,
    val exitGate: ExitGate
) {
    fun vehicleArrives(vehicle: Vehicle): Ticket =
        entranceGate.enter(building, vehicle)

    fun vehicleExits(ticket: Ticket, paymentMode: Payment) {
        exitGate.completeExit(building, ticket, paymentMode)
    }

    fun getCurrentStrategy(): CostComputation {
        return exitGate.getCurrentStrategy()
    }
    fun setCurrentStrategy(costComputation: CostComputation){
        exitGate.setCurrentStrategy(costComputation)
    }


}