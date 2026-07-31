package chaitnya.dev.domain.manager

import chaitnya.dev.domain.lookUpStrategy.ParkingSpotLookupStrategy
import chaitnya.dev.domain.lookUpStrategy.RandomSpot
import chaitnya.dev.domain.models.ParkingSpot

class TwoWheelerSpotManager (
    spots: MutableList<ParkingSpot>,
    strategy: ParkingSpotLookupStrategy = RandomSpot()
) : ParkingSpotManager(spots, strategy)
