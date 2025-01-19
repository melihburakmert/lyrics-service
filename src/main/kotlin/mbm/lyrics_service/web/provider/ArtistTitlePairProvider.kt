package mbm.lyrics_service.web.provider

import kotlin.random.Random

fun provideDefaultArtistTitlePair(): Pair<String, String> {
        return if (Random.nextBoolean()) "Rick Astley" to "Never Gonna Give You Up"
        else "Eiffel 65" to "Blue"
}