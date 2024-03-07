import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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