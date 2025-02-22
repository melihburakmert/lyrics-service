package mbm.lyrics_service.lyrics.mapper

import mbm.lyrics_service.lyrics.LyricsDto
import mbm.lyrics_service.lyrics.domain.LyricsOvhResponse
import org.springframework.stereotype.Component

@Component
class LyricsOvhMapper {
    fun lyricsOvhResponseToDto(lyricsOvhResponse: LyricsOvhResponse, artist: String, title: String): LyricsDto {
        val lyrics = lyricsOvhResponse.lyrics.replace(Regex("\\s+"), " ").trim()
        return LyricsDto(artist, title, lyrics)
    }
}