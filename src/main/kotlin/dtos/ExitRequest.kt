package chaitnya.dev.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ExitRequest(
    val ticketId: String,
    val paymentMode: String
)