package mbm.lyrics_service.subscriber.mapper

import mbm.lyrics_service.subscriber.domain.PlaybackDataDto
import mbm.lyrics_service.subscriber.message.PlaybackDataMessage
import org.springframework.stereotype.Component

@Component
class PlaybackDataMessageMapper {
    fun map(message: PlaybackDataMessage): PlaybackDataDto {
        return PlaybackDataDto(message.tracks)
    }
}