package presentation.ui.offline

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import getVideoFromMediaStore

class OffLineMovieScreen: Screen {
    @Composable
    override fun Content() {
        val getVideo = getVideoFromMediaStore()
        Box {
            Text("1111")
        }
    }

}