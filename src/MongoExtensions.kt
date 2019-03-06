import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

suspend fun <T> database(block: suspend (database: CoroutineDatabase) -> T): T {
    val database = mongoClient.getDatabase("test")
    return block(database)
}

inline fun <reified TCollection : Any, TResponse> collection(
    database: CoroutineDatabase,
    collectionName: String,
    block: (collection: CoroutineCollection<TCollection>) -> TResponse
): TResponse {
    val collection = database.getCollection<TCollection>(collectionName)
    return block(collection)
}