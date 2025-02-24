package mbm.lyrics_service.subscriber.receiver

import mbm.lyrics_service.mediator.LyricsMediator
import mbm.lyrics_service.serialization.SerializationService
import mbm.lyrics_service.subscriber.mapper.PlaybackDataMessageMapper
import mbm.lyrics_service.subscriber.message.PlaybackDataMessage
import org.springframework.stereotype.Component

@Component
class PlaybackDataReceiver(
    private val playbackDataMessageMapper: PlaybackDataMessageMapper,
    private val mediator: LyricsMediator,
    serializationService: SerializationService

): MessageReceiver<PlaybackDataMessage>(serializationService, clazz = PlaybackDataMessage::class.java) {

    override fun consume(message: PlaybackDataMessage) {
        val playbackDataDto = playbackDataMessageMapper.map(message)
        mediator.handlePlaybackDataReceived(playbackDataDto)
    }
}