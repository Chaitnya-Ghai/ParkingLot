package chaitnya.dev.parkinglot.manager

import chaitnya.dev.parkinglot.lookUpStrategy.ParkingSpotLookupStrategy
import chaitnya.dev.parkinglot.lookUpStrategy.RandomSpot
import chaitnya.dev.parkinglot.models.ParkingSpot

/**
    1. Maintains a list of Four Wheeler Spots only
    2. Has its own lookup strategy
    3. Has its own lock, to avoid conflicts with other spot managers
*/
class FourWheelerSpotManager(
    spots: MutableList<ParkingSpot>,
    strategy: ParkingSpotLookupStrategy = RandomSpot()
) : ParkingSpotManager(spots, strategy)

