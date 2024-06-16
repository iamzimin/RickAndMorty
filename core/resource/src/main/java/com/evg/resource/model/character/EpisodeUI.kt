package com.evg.resource.model.character

data class EpisodeUI(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: Pair<Int, Int>,
    val characters: List<String>,
    val url: String,
) {
    fun getEpisode(): String {
        return "Season ${episode.first}. Episode ${episode.second}"
    }
}