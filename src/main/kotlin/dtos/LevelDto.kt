package chaitnya.dev.dtos

import chaitnya.dev.domain.lld.ParkingLevel
import chaitnya.dev.domain.lookUpStrategy.ParkingSpotLookupStrategy
import chaitnya.dev.domain.manager.FourWheelerSpotManager
import chaitnya.dev.domain.manager.ParkingSpotManager
import chaitnya.dev.domain.manager.TwoWheelerSpotManager
import chaitnya.dev.domain.models.ParkingSpot
import chaitnya.dev.domain.models.VehicleType
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class LevelDto(
    val levelNumber: Int,
    val availability: MutableMap<VehicleType, Int>
)


fun LevelDto.toDomain(): ParkingLevel {
    val managerSpotMap = mutableMapOf<VehicleType, ParkingSpotManager>()
    availability.forEach { (vehicleType, spotCount) ->
        managerSpotMap[vehicleType] = getManager(vehicleType=vehicleType, spotCount= spotCount , null)
    }
    return ParkingLevel(levelNumber, managerSpotMap)
}

fun getManager(
    vehicleType: VehicleType,
    spotCount: Int,
    strategy: ParkingSpotLookupStrategy?
): ParkingSpotManager {
    val spots = List(spotCount) {
        ParkingSpot(spotId = Uuid.random().toString())
    }.toMutableList()

    return when (vehicleType) {
        VehicleType.TwoWheeler ->
            if (strategy != null) {
                TwoWheelerSpotManager(spots, strategy)
            } else {
                TwoWheelerSpotManager(spots)
            }
        VehicleType.FourWheeler ->

            if (strategy != null) {
                FourWheelerSpotManager(spots, strategy)
            } else {
                FourWheelerSpotManager(spots)
            }
    }
}