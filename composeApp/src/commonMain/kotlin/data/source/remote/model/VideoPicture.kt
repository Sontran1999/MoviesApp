package data.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoPicture(
    val id: Long,
    val nr: Long,
    val picture: String,
)