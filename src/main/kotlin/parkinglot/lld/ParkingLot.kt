package chaitnya.dev.parkinglot.lld

import chaitnya.dev.parkinglot.models.Ticket
import chaitnya.dev.parkinglot.models.Vehicle
import chaitnya.dev.payment.Payment

class ParkingLot(
    val building: ParkingLotBuilding,
    val entranceGate: EntryGate,
    val exitGate: ExitGate
) {
    fun vehicleArrives(vehicle: Vehicle){
        return entranceGate.enter(building, vehicle)
    }

    fun vehicleExits(ticket: Ticket, paymentMode: Payment) {
        exitGate.completeExit(building, ticket, paymentMode)
    }

}