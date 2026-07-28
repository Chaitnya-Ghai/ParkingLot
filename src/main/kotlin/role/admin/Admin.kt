@file:OptIn(ExperimentalUuidApi::class)

package chaitnya.dev.role.admin

//import chaitnya.dev.parkinglot.lld.ParkingLevel
//import chaitnya.dev.parkinglot.lld.ParkingLotBuilding
//import chaitnya.dev.parkinglot.lookUpStrategy.ParkingSpotLookupStrategy
//import chaitnya.dev.parkinglot.manager.ParkingSpotManager
//import chaitnya.dev.parkinglot.models.Vehicle
//import chaitnya.dev.parkinglot.models.VehicleType
//import chaitnya.dev.parkinglot.pricingStrategy.CostComputation
//import chaitnya.dev.parkinglot.pricingStrategy.PricingStrategy
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
import kotlin.uuid.ExperimentalUuidApi
//import kotlin.uuid.Uuid
//
//
//class Admin(
//    val id: String = "admin_${Uuid.generateV7()}",
//    val name: String,
//    private val building: ParkingLotBuilding
//) {
//    fun addLevel(level: ParkingLevel) {
//        building.addLevel(level)
//    }
//
//    fun removeLevel(levelNumber: Int) {
//        building.removeLevel(levelNumber)
//    }
//
//    fun setPricingStrategy(strategy: PricingStrategy) {
//        building.setPricingStrategy(strategy)
//    }
//
//    fun setLookupStrategy(strategy: ParkingSpotLookupStrategy) {
//        building.setLookupStrategy(strategy)
//    }
//}