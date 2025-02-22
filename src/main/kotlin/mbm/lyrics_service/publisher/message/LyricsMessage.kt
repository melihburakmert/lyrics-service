package mbm.lyrics_service.publisher.message

import mbm.lyrics_service.publisher.domain.Lyrics
import java.io.Serializable

data class LyricsMessage(val lyrics: List<Lyrics>): Serializable
