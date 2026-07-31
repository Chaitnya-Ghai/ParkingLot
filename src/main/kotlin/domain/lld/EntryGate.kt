package chaitnya.dev.domain.lld

import chaitnya.dev.domain.models.Ticket
import chaitnya.dev.domain.models.Vehicle

object EntryGate {
    fun enter(building: ParkingLotBuilding, vehicle: Vehicle): Ticket =
        building.allocate(vehicle)
}