package data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideosList(
    val page: Long? = null,
    @SerialName("per_page")
    val perPage: Long? = null,
    val videos: List<Video>? = null,
    @SerialName("total_results")
    val totalResults: Long? = null,
    @SerialName("next_page")
    val nextPage: String? = null,
    val url: String? = null,
)