package mbm.lyrics_service.lyrics.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "lyrics-ovh")
class LyricsOvhProperties {
    lateinit var apiUri: String
}