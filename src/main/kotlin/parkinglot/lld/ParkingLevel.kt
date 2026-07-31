package chaitnya.dev.parkinglot.lld

import chaitnya.dev.dtos.LevelResponse
import chaitnya.dev.parkinglot.manager.ParkingSpotManager
import chaitnya.dev.parkinglot.models.ParkingSpot
import chaitnya.dev.parkinglot.models.VehicleType

class ParkingLevel(
    val levelNumber: Int,
    private val managers: MutableMap<VehicleType, ParkingSpotManager>
) {
    private fun availability(): Map<VehicleType, Int> =
        managers.mapValues { (_, manager) -> manager.totalSpotCount() }

    fun toResponse(): LevelResponse =
        LevelResponse(levelNumber = levelNumber, availability = availability())

    fun hasAvailability(vehicleType: VehicleType): Boolean =
        managers[vehicleType]?.hasFreeSpot() ?: false

    fun park(vehicleType: VehicleType): ParkingSpot {
        val spotManager = managers[vehicleType]
            ?: throw IllegalArgumentException(
                "No parking manager for $vehicleType"
            )

        return spotManager.park()
            ?: throw IllegalArgumentException(
                "No parking spot available for $vehicleType"
            )
    }

    fun unPark(
        vehicleType: VehicleType,
        spot: ParkingSpot
    ) {
        managers[vehicleType]?.unPark(spot)
    }

}