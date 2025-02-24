package mbm.lyrics_service.subscriber.message

import java.io.Serializable

data class Track(val artist: String, val title: String): Serializable
