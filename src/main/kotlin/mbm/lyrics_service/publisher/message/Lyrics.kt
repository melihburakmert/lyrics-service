package mbm.lyrics_service.publisher.message

import java.io.Serializable

data class Lyrics(val track: Track, val lyrics: String): Serializable
