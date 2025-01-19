package mbm.lyrics_service.subscriber.mapper

import mbm.lyrics_service.lyrics.SongsDto
import mbm.lyrics_service.subscriber.message.RecentlyPlayedSongsMessage
import org.springframework.stereotype.Component

@Component
class RecentlyPlayedMessageMapper {
    fun map(message: RecentlyPlayedSongsMessage): SongsDto {
        return SongsDto(message.songs)
    }
}