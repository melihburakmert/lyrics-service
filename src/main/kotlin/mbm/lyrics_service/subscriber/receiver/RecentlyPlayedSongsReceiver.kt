package mbm.lyrics_service.subscriber.receiver

import mbm.lyrics_service.lyrics.LyricsService
import mbm.lyrics_service.serialization.SerializationService
import mbm.lyrics_service.subscriber.mapper.RecentlyPlayedMessageMapper
import mbm.lyrics_service.subscriber.message.RecentlyPlayedSongsMessage
import org.springframework.stereotype.Component

@Component
class RecentlyPlayedSongsReceiver(
    private val recentlyPlayedMessageMapper: RecentlyPlayedMessageMapper,
    private val lyricsService: LyricsService,
    serializationService: SerializationService

): MessageReceiver<RecentlyPlayedSongsMessage>(serializationService, clazz = RecentlyPlayedSongsMessage::class.java) {

    override fun consume(message: RecentlyPlayedSongsMessage) {
        val recentlyPlayedDto = recentlyPlayedMessageMapper.map(message)
        val lyricsInBulk = lyricsService.getLyricsInBulk(recentlyPlayedDto.songs)

        lyricsInBulk.forEach { logger.info { "Fetched lyrics: $it" } }
    }
}