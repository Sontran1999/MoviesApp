package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val id: Long,
    val width: Long,
    val height: Long,
    val duration: Long,
    @SerialName("full_res")
    val fullRes: String?,
    val tags: List<String?>,
    val url: String,
    val image: String,
    @SerialName("avg_color")
    val avgColor: String?,
    val user: User,
    @SerialName("video_files")
    val videoFiles: List<VideoFile>,
    @SerialName("video_pictures")
    val videoPictures: List<VideoPicture>,
)
