package mbm.lyrics_service.serialization

interface SerializationService {
    fun <T> deserialize(message: String?, clazz: Class<T>): T
    fun <T : Any> serialize(message: T): String?

}
