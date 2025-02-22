package mbm.lyrics_service.publisher.imp

import mbm.lyrics_service.lyrics.LyricsDto
import mbm.lyrics_service.publisher.PublisherService
import mbm.lyrics_service.publisher.configuration.PublisherMqProperties
import mbm.lyrics_service.publisher.mapper.LyricsMessageMapper
import mbm.lyrics_service.serialization.SerializationService
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.stereotype.Service

@Service
class PublisherServiceImp(
    private val streamBridge: StreamBridge,
    private val serializationService: SerializationService,
    private val publisherMqProperties: PublisherMqProperties,
    private val lyricsMessageMapper: LyricsMessageMapper
    ) : PublisherService {

    override fun publishLyrics(lyrics: List<LyricsDto>) {
        val lyricsMessage = lyricsMessageMapper.map(lyrics)
        val serializedMessage = serializationService.serialize(lyricsMessage)
        streamBridge.send(publisherMqProperties.lyricsTopicName, serializedMessage)
    }
}