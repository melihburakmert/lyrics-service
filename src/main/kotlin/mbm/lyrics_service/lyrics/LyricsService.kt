package mbm.lyrics_service.lyrics

import mbm.lyrics_service.domain.SongItem

interface LyricsService {
    fun getLyrics(artist: String, title: String): LyricsDto

    fun getLyricsInBulk(songs: List<SongItem>): List<LyricsDto>
}