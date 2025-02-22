package mbm.lyrics_service.subscriber.receiver

import mbm.lyrics_service.lyrics.LyricsService
import mbm.lyrics_service.serialization.SerializationService
import mbm.lyrics_service.subscriber.mapper.PlaybackDataMessageMapper
import mbm.lyrics_service.subscriber.message.PlaybackDataMessage
import org.springframework.stereotype.Component

@Component
class PlaybackDataReceiver(
    private val playbackDataMessageMapper: PlaybackDataMessageMapper,
    private val lyricsService: LyricsService,
    serializationService: SerializationService

): MessageReceiver<PlaybackDataMessage>(serializationService, clazz = PlaybackDataMessage::class.java) {

    override fun consume(message: PlaybackDataMessage) {
        val playbackDataDto = playbackDataMessageMapper.map(message)
        val lyricsDtos = lyricsService.getLyricsInBulk(playbackDataDto.tracks)

        lyricsDtos.forEach { logger.info { "Fetched lyrics: $it" } }
    }
}