import com.mongodb.MongoClientException
import org.koin.dsl.module.applicationContext
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


val appModule = applicationContext {
    bean { getMongoClient() }
}

fun getMongoClient(): CoroutineClient {
    return KMongo.createClient("mongodb://127.0.0.1:32770").coroutine
}