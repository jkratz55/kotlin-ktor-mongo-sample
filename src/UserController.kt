import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.Location
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import org.bson.codecs.pojo.annotations.BsonId
import org.koin.ktor.ext.inject
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.newId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

fun Route.userRoutes() {

    val logger: Logger = LoggerFactory.getLogger("UserController")
    val client: CoroutineClient by inject()

    route("/users") {

        get("") {
            val users = client.getDatabase("test")
                .getCollection<User>("users")
                .find()
                .toList()
            call.respond(HttpStatusCode.OK, users)
        }

        post<CreateUserRequest>("") {request ->
            val user = User(userName = request.userName,
                password = request.password,
                email = request.email)
            client.getDatabase("test")
                .getCollection<User>("users")
                .insertOne(user)
            call.respond(HttpStatusCode.OK)
        }
    }
}

data class User(
    @BsonId val id: UUID = UUID.randomUUID(),
    val userName: String,
    val password: String,
    val email: String
)

@Location("")
data class CreateUserRequest(val userName: String,
                             val password: String,
                             val email: String)