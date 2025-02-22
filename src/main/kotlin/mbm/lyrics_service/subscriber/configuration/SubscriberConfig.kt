package mbm.lyrics_service.subscriber.configuration

import mbm.lyrics_service.subscriber.receiver.PlaybackDataReceiver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message

@Configuration
class SubscriberConfig {

    @Bean
    fun playbackDataConsumer(receiver: PlaybackDataReceiver): (Message<String>) -> Unit {
        return receiver::receive
    }
}