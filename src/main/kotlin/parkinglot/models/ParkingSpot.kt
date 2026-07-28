package chaitnya.dev.parkinglot.models

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Serializable
class ParkingSpot(
    val spotId: String
) {
    private var isAvailable = true

    val isSpotFree: Boolean
        get() = isAvailable

    fun occupySpot() {
        isAvailable = false
    }

    fun releaseSpot() {
        isAvailable = true
    }
}