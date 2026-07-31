package chaitnya.dev.parkinglot.lld

import chaitnya.dev.parkinglot.models.Ticket
import chaitnya.dev.parkinglot.models.Vehicle

object EntryGate {
    fun enter(building: ParkingLotBuilding, vehicle: Vehicle): Ticket =
        building.allocate(vehicle)
}