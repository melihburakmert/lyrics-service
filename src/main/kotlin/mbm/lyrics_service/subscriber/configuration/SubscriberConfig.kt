package mbm.lyrics_service.subscriber.configuration

import mbm.lyrics_service.subscriber.receiver.RecentlyPlayedSongsReceiver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message

@Configuration
class SubscriberConfig {

    @Bean
    fun recentlyPlayedSongsConsumer(receiver: RecentlyPlayedSongsReceiver): (Message<String>) -> Unit {
        return receiver::receive
    }
}