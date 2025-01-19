package mbm.lyrics_service.web.mapper

import mbm.lyrics_service.lyrics.LyricsDto
import mbm.lyrics_service.web.domain.LyricsResponse
import org.springframework.stereotype.Component

@Component
class LyricsResponseMapper {

    fun toResponse(lyricsDto: LyricsDto): LyricsResponse {
        val (artist, title, lyrics) = lyricsDto
        return LyricsResponse(artist, title, lyrics)
    }
}