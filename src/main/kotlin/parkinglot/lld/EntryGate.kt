package chaitnya.dev.parkinglot.lld

import chaitnya.dev.parkinglot.models.Vehicle

object EntryGate {
    fun enter(building: ParkingLotBuilding, vehicle: Vehicle) {
        building.allocate(vehicle)
    }
}