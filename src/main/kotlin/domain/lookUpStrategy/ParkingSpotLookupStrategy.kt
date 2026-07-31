package chaitnya.dev.domain.lookUpStrategy

import chaitnya.dev.domain.models.ParkingSpot


interface ParkingSpotLookupStrategy {
    fun selectSpot(spots: MutableList<ParkingSpot>?): ParkingSpot?
}