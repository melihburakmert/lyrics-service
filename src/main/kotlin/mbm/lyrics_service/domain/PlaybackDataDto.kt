package mbm.lyrics_service.domain

data class PlaybackDataDto(val tracks: List<TrackDto>, val sessionId: String) {
}