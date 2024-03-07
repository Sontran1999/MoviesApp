package data.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoPicture(
    val id: Long,
    val nr: Long,
    val picture: String,
)