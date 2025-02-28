package mbm.lyrics_service.publisher.message

import java.io.Serializable

data class LyricsMessage(val lyrics: List<Lyrics>, val sessionId: String): Serializable
