package mbm.lyrics_service.publisher

import mbm.lyrics_service.lyrics.LyricsDto

interface PublisherService {
    fun publishLyrics(lyrics: List<LyricsDto>)
}