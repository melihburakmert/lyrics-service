package mbm.lyrics_service.publisher.mapper

import mbm.lyrics_service.domain.LyricsDataDto
import mbm.lyrics_service.publisher.message.Lyrics
import mbm.lyrics_service.publisher.message.LyricsMessage
import mbm.lyrics_service.publisher.message.Track
import org.springframework.stereotype.Component

@Component
class LyricsMessageMapper {
    fun map(lyricsDataDto: LyricsDataDto): LyricsMessage =
        LyricsMessage(lyricsDataDto.lyrics.map { Lyrics(Track(it.track.artist, it.track.title), it.lyrics) })
}