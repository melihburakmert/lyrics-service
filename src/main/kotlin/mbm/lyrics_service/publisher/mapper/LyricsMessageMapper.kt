package mbm.lyrics_service.publisher.mapper

import mbm.lyrics_service.lyrics.LyricsDto
import mbm.lyrics_service.publisher.domain.Lyrics
import mbm.lyrics_service.publisher.message.LyricsMessage
import org.springframework.stereotype.Component

@Component
class LyricsMessageMapper {
    fun map(lyrics: List<LyricsDto>): LyricsMessage =
        LyricsMessage(lyrics.map { Lyrics(it.artist, it.title, it.lyrics) })
}