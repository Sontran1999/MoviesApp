package presentation.video

import VideoPlayer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.lifecycle.ScreenLifecycleOwner
import cafe.adriel.voyager.core.screen.Screen

class VideoScreen(private val url: String) : Screen, ScreenLifecycleOwner {
    @Composable
    override fun Content() {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            VideoPlayer(url)
        }
    }
}