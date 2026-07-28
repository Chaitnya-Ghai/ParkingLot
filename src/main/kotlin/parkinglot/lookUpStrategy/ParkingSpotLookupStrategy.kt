package chaitnya.dev.parkinglot.lookUpStrategy

import chaitnya.dev.parkinglot.models.ParkingSpot


interface ParkingSpotLookupStrategy {
    fun selectSpot(spots: MutableList<ParkingSpot>?): ParkingSpot?
}