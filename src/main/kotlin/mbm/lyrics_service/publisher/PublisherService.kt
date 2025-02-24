package mbm.lyrics_service.publisher

import mbm.lyrics_service.domain.LyricsDataDto

interface PublisherService {
    fun publishLyrics(lyricsDataDto: LyricsDataDto)
}