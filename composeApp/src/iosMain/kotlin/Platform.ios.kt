import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

@Composable
actual fun VideoPlayer(url: String) {
    // Platform-specific implementation here
}

actual class LocalVideoDataSource {
    actual suspend fun getVideoList(): List<data.source.local.model.VideoEntity>? {
        TODO("Not yet implemented")
    }

}

@Composable
actual fun getVideoThumbnail(videoUri: String): ImageBitmap? {
    TODO("Not yet implemented")
}