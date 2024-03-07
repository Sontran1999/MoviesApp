package presentation.video

import VideoPlayer
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class VideoScreen(private val url: String) : Screen {
    @Composable
    override fun Content() {
        VideoPlayer(url)
    }
}