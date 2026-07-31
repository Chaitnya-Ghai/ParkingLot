package chaitnya.dev.parkinglot.manager

import chaitnya.dev.parkinglot.lookUpStrategy.ParkingSpotLookupStrategy
import chaitnya.dev.parkinglot.lookUpStrategy.RandomSpot
import chaitnya.dev.parkinglot.models.ParkingSpot

class TwoWheelerSpotManager (
    spots: MutableList<ParkingSpot>,
    strategy: ParkingSpotLookupStrategy = RandomSpot()
) : ParkingSpotManager(spots, strategy)
