package mbm.lyrics_service.subscriber.receiver

import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders
import io.github.oshai.kotlinlogging.KotlinLogging
import mbm.lyrics_service.serialization.SerializationService
import org.springframework.messaging.Message

abstract class MessageReceiver<T>(
    private val serializationService: SerializationService,
    private val clazz: Class<T>
) {

    val logger = KotlinLogging.logger {}

    companion object {
        private const val INFO_MESSAGE = "Pulled %s message: %s"
        private const val CONSUME_ERROR_MESSAGE = "Error while consuming message:\n%s\n"
        private const val SERIALIZATION_ERROR_MESSAGE = "Error while deserializing message:\n%s\n"
        private const val ACKNOWLEDGE_ERROR_MESSAGE = "Failed to acknowledge message: %s"
    }

    fun receive(message: Message<String>) {
        val payload = message.payload
        acknowledgeIfPresent(message, payload)
        logger.info { INFO_MESSAGE.format(clazz.simpleName, payload) }

        runCatching { serializationService.deserialize(payload, clazz) }
            .onSuccess { consumeSafely(it, payload) }
            .onFailure { logError(SERIALIZATION_ERROR_MESSAGE, payload, it) }
    }

    private fun acknowledgeIfPresent(message: Message<String>, payload: String) {
        GcpPubSubHeaders.getOriginalMessage(message).ifPresent {
            runCatching { it.ack() }
                .onFailure { logError(ACKNOWLEDGE_ERROR_MESSAGE, payload, it) }
        }
    }

    private fun consumeSafely(message: T, payload: String) {
        runCatching { consume(message) }
            .onFailure { logError(CONSUME_ERROR_MESSAGE, payload, it) }
    }

    private fun logError(messageTemplate: String, payload: String, exception: Throwable) {
        logger.error(exception) { messageTemplate.format(payload) }
    }

    abstract fun consume(message: T)
}
