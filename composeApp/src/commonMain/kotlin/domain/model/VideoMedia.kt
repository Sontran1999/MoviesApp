package domain.model

data class VideoMedia (
    val id: Long,
    val name: String,
    val url: String,
    val duration: Long,
    val picture: String,
)