package chaitnya.dev.parkinglot.lookUpStrategy

import chaitnya.dev.parkinglot.models.ParkingSpot

class RandomSpot : ParkingSpotLookupStrategy {

    override fun selectSpot(spots: MutableList<ParkingSpot>?): ParkingSpot? {
        return spots?.firstOrNull { it.isSpotFree }
    }
}