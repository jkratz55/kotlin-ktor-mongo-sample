import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun Route.userRoutes() {

    val logger: Logger = LoggerFactory.getLogger("UserController")

    route("/users") {

        get("") {
            val r = mapOf<String, Any>(
                "name" to "Joe Somebody",
                "age" to 31
            )
            call.respond(HttpStatusCode.OK, r)
        }

        post("") {
            call.respond(HttpStatusCode.OK)
        }
    }
}

data class User(
    val userName: String,
    val password: String,
    val email: String
)