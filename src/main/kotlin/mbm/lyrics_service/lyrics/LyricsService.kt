package mbm.lyrics_service.lyrics

import mbm.lyrics_service.domain.Track

interface LyricsService {
    fun getLyrics(artist: String, title: String): LyricsDto

    fun getLyricsInBulk(songs: List<Track>): List<LyricsDto>
}