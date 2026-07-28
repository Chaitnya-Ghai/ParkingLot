package chaitnya.dev.parkinglot.manager

import chaitnya.dev.parkinglot.lookUpStrategy.ParkingSpotLookupStrategy
import chaitnya.dev.parkinglot.models.ParkingSpot
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.locks.ReentrantLock
import io.ktor.utils.io.locks.withLock
import kotlinx.serialization.Serializable


@OptIn(InternalAPI::class)
abstract class ParkingSpotManager(
    protected val spots: MutableList<ParkingSpot>,
    protected val strategy: ParkingSpotLookupStrategy
) {
    @Transient
    protected val lock = ReentrantLock()
    fun park(): ParkingSpot? = lock.withLock {
        val spot = strategy.selectSpot(spots)
        spot?.occupySpot()
        return spot
    }

    fun unPark(spot: ParkingSpot) = lock.withLock {
        spot.releaseSpot()
    }

    fun hasFreeSpot(): Boolean = lock.withLock {
        spots.any { it.isSpotFree }
    }

}