package chaitnya.dev.plugins

import chaitnya.dev.dtos.LevelDto
import chaitnya.dev.domain.models.VehicleType
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult
import io.ktor.server.routing.Route

fun Route.adminRouteValidations(){
    install(RequestValidation) {
        validate<LevelDto> { body ->

            when {
                body.levelNumber < 1 ->
                    ValidationResult.Invalid("Level number must be greater than 0.")

                body.availability.values.any { it < 1 } ->
                    ValidationResult.Invalid("Number of slots cannot be negative.")

                body.availability.keys.any {
                    it != VehicleType.TwoWheeler &&
                            it != VehicleType.FourWheeler
                } ->
                    ValidationResult.Invalid("Only TwoWheeler and FourWheeler are allowed.")

                else ->
                    ValidationResult.Valid
            }
        }
    }

}