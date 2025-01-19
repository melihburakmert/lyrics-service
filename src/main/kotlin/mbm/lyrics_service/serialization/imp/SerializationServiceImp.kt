package mbm.lyrics_service.serialization.imp

import com.fasterxml.jackson.databind.ObjectMapper
import mbm.lyrics_service.serialization.SerializationService
import org.springframework.stereotype.Component

@Component
class SerializationServiceImp(
    private val objectMapper: ObjectMapper
) : SerializationService {

    override fun <T> deserialize(message: String?, clazz: Class<T>): T {
        return runCatching {
            objectMapper.readValue(message, clazz)
        }.getOrElse {
            throw MessageMapperDeserializationException(clazz, message, it)
        }
    }

    override fun <T : Any> serialize(message: T): String {
        return runCatching {
            objectMapper.writeValueAsString(message)
        }.getOrElse {
            throw MessageMapperSerializationException(message, it)
        }
    }

    private class MessageMapperDeserializationException(
        clazz: Class<*>, message: String?, cause: Throwable?
    ) : RuntimeException(
        "Unable to deserialize ${clazz.simpleName} message: $message", cause
    )

    private class MessageMapperSerializationException(
        message: Any, cause: Throwable?
    ) : RuntimeException(
        "Unable to serialize ${message::class.simpleName} message: $message", cause
    )
}