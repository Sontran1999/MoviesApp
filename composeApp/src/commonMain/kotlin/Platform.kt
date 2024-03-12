import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import data.source.local.model.VideoEntity

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

@Composable
expect fun VideoPlayer(url: String)

expect class LocalVideoDataSource {
    suspend fun getVideoList(): List<VideoEntity>?

}

@Composable
expect fun getVideoThumbnail(videoUri: String): ImageBitmap?