package mbm.lyrics_service.subscriber.mapper

import mbm.lyrics_service.domain.PlaybackDataDto
import mbm.lyrics_service.domain.TrackDto
import mbm.lyrics_service.subscriber.message.PlaybackDataMessage
import org.springframework.stereotype.Component

@Component
class PlaybackDataMessageMapper {
    fun map(message: PlaybackDataMessage): PlaybackDataDto {
        val tracks = message.tracks.map { TrackDto(it.artist, it.title) }
        return PlaybackDataDto(tracks)
    }
}