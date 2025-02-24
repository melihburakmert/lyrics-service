package mbm.lyrics_service.web.mapper

import mbm.lyrics_service.domain.LyricsDto
import mbm.lyrics_service.web.domain.LyricsResponse
import mbm.lyrics_service.web.domain.Track
import org.springframework.stereotype.Component

@Component
class LyricsResponseMapper {

    fun toResponse(lyricsDto: LyricsDto): LyricsResponse {
        val (trackDto, lyrics) = lyricsDto
        val (artist, track) = trackDto
        return LyricsResponse(Track( artist, track), lyrics)
    }
}