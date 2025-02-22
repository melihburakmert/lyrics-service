package mbm.lyrics_service.lyrics.service

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import mbm.lyrics_service.domain.Track
import mbm.lyrics_service.lyrics.LyricsDto
import mbm.lyrics_service.lyrics.LyricsService
import mbm.lyrics_service.lyrics.configuration.LyricsOvhProperties
import mbm.lyrics_service.lyrics.domain.LyricsOvhResponse
import mbm.lyrics_service.lyrics.mapper.LyricsOvhMapper
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

private val logger = KotlinLogging.logger {}

@Service
class LyricsOvhService(
    private val restClient: RestClient,
    private val lyricsOvhProperties: LyricsOvhProperties,
    private val lyricsDtoMapper: LyricsOvhMapper
) : LyricsService {

    override fun getLyricsInBulk(songs: List<Track>): List<LyricsDto> = runBlocking {
        logger.info { "Starting bulk fetch for ${songs.size} songs" }

        val lyricsList = songs.map { song ->
            rateLimit(5000) {
                getLyrics(song.artist, song.title)
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
                LyricsDto(artist, title, "")
            }
        )
    }

    private fun getLyricsOvhResponse(uri: String): Result<LyricsOvhResponse> = runCatching {
        restClient.get()
            .uri(uri)
            .retrieve()
            .body<LyricsOvhResponse>() ?: throw IllegalStateException("Response body is null")
    }.recoverCatching { e ->
        handleNotFoundOrPropagate(e, uri)
    }

    private fun handleNotFoundOrPropagate(e: Throwable, uri: String): LyricsOvhResponse {
        return if (e is HttpClientErrorException.NotFound) {
            logger.warn { "Lyrics not found for URI: $uri - ${e.message}" }
            LyricsOvhResponse("")
        } else {
            throw e
        }
    }

    private fun buildUri(artist: String, title: String) = "${lyricsOvhProperties.apiUri}/$artist/$title"

    private suspend fun <T> rateLimit(intervalMs: Long, block: suspend () -> T): T {
        delay(intervalMs)
        return block()
    }
}
