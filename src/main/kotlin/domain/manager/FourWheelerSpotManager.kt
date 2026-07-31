package chaitnya.dev.domain.manager

import chaitnya.dev.domain.lookUpStrategy.ParkingSpotLookupStrategy
import chaitnya.dev.domain.lookUpStrategy.RandomSpot
import chaitnya.dev.domain.models.ParkingSpot

/**
    1. Maintains a list of Four Wheeler Spots only
    2. Has its own lookup strategy
    3. Has its own lock, to avoid conflicts with other spot managers
*/
class FourWheelerSpotManager(
    spots: MutableList<ParkingSpot>,
    strategy: ParkingSpotLookupStrategy = RandomSpot()
) : ParkingSpotManager(spots, strategy)

