package mbm.lyrics_service.subscriber.message

import mbm.lyrics_service.domain.SongItem
import java.io.Serializable

data class RecentlyPlayedSongsMessage(val songs: List<SongItem>): Serializable
