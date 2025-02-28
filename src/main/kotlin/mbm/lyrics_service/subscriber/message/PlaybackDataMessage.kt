package mbm.lyrics_service.subscriber.message

import java.io.Serializable

data class PlaybackDataMessage(val tracks: List<Track>, val sessionId: String): Serializable
