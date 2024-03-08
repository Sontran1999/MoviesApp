import android.net.Uri
import android.os.Build
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Composable
actual fun VideoPlayer(url: String) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    val mediaItem = MediaItem.fromUri(Uri.parse(url))
    exoPlayer.setMediaItem(mediaItem)


    val playerView = StyledPlayerView(context)
    playerView.player = exoPlayer

    DisposableEffect(
        AndroidView(modifier = Modifier.fillMaxSize(),
            factory = {playerView})
    ){

        exoPlayer.prepare()
        exoPlayer.playWhenReady= true

        onDispose {
            exoPlayer.release()
        }
    }



//    AndroidView(
//        modifier = Modifier.fillMaxWidth().height(400.dp),
//        factory = { context ->
//            VideoView(context).apply {
//                setVideoPath(url)
//                val mediaController = MediaController(context)
//                mediaController.setAnchorView(this)
//                setMediaController(mediaController)
//                start()
//            }
//        },
//        update = {})
}