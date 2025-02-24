package mbm.lyrics_service.lyrics

import mbm.lyrics_service.domain.LyricsDataDto
import mbm.lyrics_service.domain.LyricsDto
import mbm.lyrics_service.domain.TrackDto

interface LyricsService {
    fun getLyrics(artist: String, title: String): LyricsDto

    fun getLyricsInBulk(tracks: List<TrackDto>): LyricsDataDto
}