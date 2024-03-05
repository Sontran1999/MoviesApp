package data.model

import kotlinx.serialization.json.JsonNames

data class Video(
    val id: Long,
    val width: Long,
    val height: Long,
    val duration: Long,
    @JsonNames("full_res")
    val fullRes: Any?,
    val tags: List<Any?>,
    val url: String,
    val image: String,
    @JsonNames("avg_color")
    val avgColor: Any?,
    val user: User,
    @JsonNames("video_files")
    val videoFiles: List<VideoFile>,
    @JsonNames("video_pictures")
    val videoPictures: List<VideoPicture>,
)
