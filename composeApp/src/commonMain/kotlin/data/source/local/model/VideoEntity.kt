package data.source.local.model

data class VideoEntity (
    val id: Long,
    val name: String,
    val url: String,
    val duration: Long,
    val picture: String,
)