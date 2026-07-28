package chaitnya.dev.parkinglot.models

import chaitnya.dev.parkinglot.lld.ParkingLevel
import kotlinx.serialization.Serializable
import kotlin.time.Clock
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid.Companion.generateV7

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class Ticket(
    val ticketId: String = generateV7().toString(),
    val vehicle: Vehicle,
    val level: Int,
    val parkingSpot: ParkingSpot,
    val entryTime: Instant = Clock.System.now()
)
