package org.example.project

import android.app.PictureInPictureParams
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Rational
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import cafe.adriel.voyager.navigator.Navigator
import org.example.project.common.ExoPlayerManager
import presentation.ui.home.HomeScreen

class MainActivity : ComponentActivity() {

    private lateinit var exoPlayer: ExoPlayer

    class MyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            println("Clicked on PIP action")
        }
    }

    private val isPipSupported by lazy {
        packageManager.hasSystemFeature(
            PackageManager.FEATURE_PICTURE_IN_PICTURE
        )
    }

    private var videoViewBounds = Rect()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exoPlayer = ExoPlayerManager.getInstance(this).exoPlayer
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize().navigationBarsPadding(),
            ) {
                Navigator(HomeScreen())
            }
        }
    }

    private fun updatedPipParams(): PictureInPictureParams? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PictureInPictureParams.Builder()
                .setSourceRectHint(videoViewBounds)
                .setAspectRatio(Rational(16, 9))
                .build()
        } else null
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        if (!isPipSupported) {
            return
        }
        if (exoPlayer.isPlaying) {
            updatedPipParams()?.let { params ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    enterPictureInPictureMode(params)
                }
            }
        }
    }
}