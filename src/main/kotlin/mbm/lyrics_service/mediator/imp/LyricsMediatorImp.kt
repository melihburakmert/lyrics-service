package mbm.lyrics_service.mediator.imp

import mbm.lyrics_service.domain.PlaybackDataDto
import mbm.lyrics_service.lyrics.LyricsService
import mbm.lyrics_service.mediator.LyricsMediator
import mbm.lyrics_service.publisher.PublisherService
import org.springframework.stereotype.Service

@Service
class LyricsMediatorImp(
    private val lyricsService: LyricsService,
    private val publisherService: PublisherService
) : LyricsMediator {
    override fun handlePlaybackDataReceived(playbackDataDto: PlaybackDataDto) {
        val lyricsDataDto = lyricsService.getLyricsInBulk(playbackDataDto.tracks)
        publisherService.publishLyrics(lyricsDataDto, playbackDataDto.sessionId)
    }
}