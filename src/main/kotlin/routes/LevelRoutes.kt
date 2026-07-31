package chaitnya.dev.routes

import chaitnya.dev.dtos.LevelDto
import chaitnya.dev.dtos.toDomain
import chaitnya.dev.domain.lld.ParkingLotBuilding
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.levelsRoutes(building: ParkingLotBuilding) {
    post("levels") {
        val req = call.receive<LevelDto>()
        building.addLevel(req.toDomain())
        call.respond(HttpStatusCode.Created, "$req has been successfully created")
    }
    get("/levels") {
        val response = building.getList().map { it.toResponse() }
        return@get call.respond(HttpStatusCode.OK, response)
    }
    delete("/levels/{id}") {
        val levelId = call.parameters["id"]?.toIntOrNull()
            ?: return@delete call.respond(
                HttpStatusCode.BadRequest,
                "Invalid level id"
            )
        building.removeLevel(levelId)
        call.respond(HttpStatusCode.OK, "level $levelId deleted")
    }
}
