package mbm.lyrics_service.publisher.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "publisher-mq")
class PublisherMqProperties {
    lateinit var lyricsTopicName: String
}