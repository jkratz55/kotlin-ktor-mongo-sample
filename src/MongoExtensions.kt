import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

fun <T> mongo(
    block: (database: CoroutineDatabase) -> T
): T {
    val database = mongoClient.getDatabase("test")
    return block(database)
}

fun mongo(
    block: (database: CoroutineDatabase) -> Unit
) {
    val database = mongoClient.getDatabase("test")
    block(database)
}

inline fun <reified T : Any> mongo(
    collectionName: String,
    block: (collection: CoroutineCollection<T>) -> T
): T {
    val database = mongoClient.getDatabase("test")
    val collection = database.getCollection<T>(collectionName)
    return block(collection)
}

inline fun <reified T : Any> mongo(
    collectionName: String,
    block: (collection: CoroutineCollection<T>) -> Unit
) {
    val database = mongoClient.getDatabase("test")
    val collection = database.getCollection<T>(collectionName)
    block(collection)
}

inline fun <reified TCollection: Any, TReturn> mongo(
    collectionName: String,
    block: (collection: CoroutineCollection<TCollection> -> TReturn)
) {
    val database = mongoClient.getDatabase("test")
    val collection = database.getCollection<T>(collectionName)
    block(collection)
}