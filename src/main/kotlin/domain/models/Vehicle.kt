package chaitnya.dev.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Vehicle(
    val vehicleNo : String,
    val vehicleType : VehicleType
)

enum class VehicleType{
    TwoWheeler , FourWheeler
}
