package chaitnya.dev.dtos

import chaitnya.dev.parkinglot.models.VehicleType
import kotlinx.serialization.Serializable

@Serializable
data class LevelResponse(
    val levelNumber: Int,
    val availability: Map<VehicleType, Int>
)

