package mbm.lyrics_service.lyrics.service

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import mbm.lyrics_service.domain.SongItem
import mbm.lyrics_service.lyrics.LyricsDto
import mbm.lyrics_service.lyrics.LyricsService
import mbm.lyrics_service.lyrics.configuration.LyricsOvhProperties
import mbm.lyrics_service.lyrics.domain.LyricsOvhResponse
import mbm.lyrics_service.lyrics.mapper.LyricsOvhMapper
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

private val logger = KotlinLogging.logger {}

@Service
class LyricsOvhService(
    private val restClient: RestClient,
    private val lyricsOvhProperties: LyricsOvhProperties,
    private val lyricsDtoMapper: LyricsOvhMapper
) : LyricsService {

    override fun getLyricsInBulk(songs: List<SongItem>): List<LyricsDto> = runBlocking {
        logger.info { "Starting bulk fetch for ${songs.size} songs" }

        val lyricsList = songs.map { song ->
            rateLimit(5000) {
                getLyrics(song.artist, song.track)
            }
        }
        logger.info { "Completed bulk fetch for ${songs.size} songs" }

        return@runBlocking lyricsList
    }

    override fun getLyrics(artist: String, title: String): LyricsDto {
        val uri = buildUri(artist, title)
        val lyricsResponse = getLyricsOvhResponse(uri)

        return lyricsResponse.fold(
            onSuccess = { response -> lyricsDtoMapper.lyricsOvhResponseToDto(response, artist, title) },
            onFailure = { e ->
                logger.error(e) { "Error while fetching lyrics from URI: $uri" }
                LyricsDto(artist, title, "Lyrics not found")
            }
        )
    }

    private fun getLyricsOvhResponse(uri: String): Result<LyricsOvhResponse> = runCatching {
        val response = restClient.get()
            .uri(uri)
            .retrieve()
            .body<LyricsOvhResponse>()
        response ?: throw IllegalStateException("Response body is null")
    }

    private fun buildUri(artist: String, title: String) = "${lyricsOvhProperties.apiUri}/$artist/$title"

    private suspend fun <T> rateLimit(intervalMs: Long, block: suspend () -> T): T {
        delay(intervalMs)
        return block()
    }
}
