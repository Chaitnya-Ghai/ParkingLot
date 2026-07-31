package chaitnya.dev.domain.lld

import chaitnya.dev.domain.models.Ticket
import chaitnya.dev.domain.models.Vehicle
import chaitnya.dev.domain.pricingStrategy.CostComputation
import chaitnya.dev.domain.payment.Payment

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