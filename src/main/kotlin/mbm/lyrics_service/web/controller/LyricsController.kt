package mbm.lyrics_service.web.controller

import mbm.lyrics_service.lyrics.LyricsService
import mbm.lyrics_service.web.domain.LyricsResponse
import mbm.lyrics_service.web.mapper.LyricsResponseMapper
import mbm.lyrics_service.web.provider.provideDefaultArtistTitlePair
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/lyrics")
class LyricsController(
    private val lyricsService: LyricsService,
    private val lyricsResponseMapper: LyricsResponseMapper
) {

    @GetMapping("")
    fun getLyrics(
        @RequestParam artist: String?,
        @RequestParam title: String?
    ): LyricsResponse {
        val (nonNullArtist, nonNullTitle) = defaultIfNull(artist, title)

        val lyricsDto = lyricsService.getLyrics(nonNullArtist, nonNullTitle)
        return lyricsResponseMapper.toResponse(lyricsDto)
    }

    private fun defaultIfNull(artist: String?, title: String?): Pair<String, String> {
        return if (artist == null || title == null) provideDefaultArtistTitlePair()
            else artist to title
    }
}
