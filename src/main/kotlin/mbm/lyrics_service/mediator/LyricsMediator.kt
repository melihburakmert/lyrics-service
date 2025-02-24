package mbm.lyrics_service.mediator

import mbm.lyrics_service.domain.PlaybackDataDto

// event payloads are too large to be serialized and persisted in a sql database
// mongodb brings other overheads..
// so we will use a mediator pattern to decouple the modules

interface LyricsMediator {
    fun handlePlaybackDataReceived(playbackDataDto: PlaybackDataDto)
}