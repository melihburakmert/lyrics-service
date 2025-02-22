package mbm.lyrics_service.subscriber.message

import mbm.lyrics_service.domain.Track
import java.io.Serializable

data class PlaybackDataMessage(val tracks: List<Track>): Serializable
