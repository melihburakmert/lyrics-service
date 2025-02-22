package mbm.lyrics_service.subscriber.domain

import mbm.lyrics_service.domain.Track

data class PlaybackDataDto(val tracks: List<Track>) {
}