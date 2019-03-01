import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.userRoutes() {
    route("/users") {
        get("") {
            val r = mapOf<String, Any>("name" to "Joe Somebody",
                "age" to 31)
            call.respond(HttpStatusCode.OK, r)
        }
    }
}