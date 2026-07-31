package chaitnya.dev.domain.lookUpStrategy

import chaitnya.dev.domain.models.ParkingSpot

class RandomSpot : ParkingSpotLookupStrategy {

    override fun selectSpot(spots: MutableList<ParkingSpot>?): ParkingSpot? {
        return spots?.firstOrNull { it.isSpotFree }
    }
}