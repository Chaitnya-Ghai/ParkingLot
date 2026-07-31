package chaitnya.dev.domain.manager

import chaitnya.dev.domain.lookUpStrategy.ParkingSpotLookupStrategy
import chaitnya.dev.domain.lookUpStrategy.RandomSpot
import chaitnya.dev.domain.models.ParkingSpot
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.locks.ReentrantLock
import io.ktor.utils.io.locks.withLock


@OptIn(InternalAPI::class)
abstract class ParkingSpotManager(
    protected val spots: MutableList<ParkingSpot>,
    protected val strategy: ParkingSpotLookupStrategy = RandomSpot()
) {
    @Transient
    protected val lock = ReentrantLock()
    fun totalSpotCount(): Int = spots.size
    fun park(): ParkingSpot? = lock.withLock {
        val spot = strategy.selectSpot(spots)
        spot?.occupySpot()
        return spot
    }

    fun unPark(spot: ParkingSpot) = lock.withLock {
        spot.releaseSpot()
    }

    fun freeSpotCount(): Int = lock.withLock {
        spots.count { it.isSpotFree }
    }

    fun hasFreeSpot(): Boolean = lock.withLock {
        spots.any { it.isSpotFree }
    }

}