package data.model

import kotlinx.serialization.SerialName

data class VideoFile(
    val id: Long,
    val quality: String,
    @SerialName("file_type")
    val fileType: String,
    val width: Long,
    val height: Long,
    val fps: Double?,
    val link: String,
)