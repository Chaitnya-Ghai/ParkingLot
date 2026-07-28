package chaitnya.dev

import chaitnya.dev.role.admin.Admin
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configAppRouting() {
    routing {
        // admin-routes
        post(""){
            val admin = Admin()
        }
        // employees-routes
    }
}