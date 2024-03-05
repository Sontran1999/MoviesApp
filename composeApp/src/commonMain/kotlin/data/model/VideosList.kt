package data.model

import kotlinx.serialization.json.JsonNames

data class VideosList(
    val page: Long,
    @JsonNames("per_page")
    val perPage: Long,
    val videos: List<Video>,
    @JsonNames("total_results")
    val totalResults: Long,
    @JsonNames("next_page")
    val nextPage: String,
    val url: String,
)