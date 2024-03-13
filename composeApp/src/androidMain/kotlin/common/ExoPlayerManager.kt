package common

import android.app.NotificationManager.IMPORTANCE_LOW
import android.content.Context
import androidx.annotation.OptIn
import androidx.core.app.NotificationCompat
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.ui.PlayerNotificationManager
import org.example.project.R

class ExoPlayerManager(context: Context) {
    companion object {
        private var instance: ExoPlayerManager? = null
        fun getInstance(context: Context): ExoPlayerManager {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = ExoPlayerManager(context)
                    }
                }
            }
            return instance!!
        }
    }

    val exoPlayer = ExoPlayer.Builder(context).build()
    val session = MediaSession.Builder(context, exoPlayer).build()
    private val channelId = "ExoPlayerChannelId"
    private val notificationId = 1234

    @UnstableApi
    private var notificationManager =
        PlayerNotificationManager.Builder(context, notificationId, channelId)
            .setChannelImportance(IMPORTANCE_LOW).build()

    @OptIn(UnstableApi::class)
    fun createNotificationChannel() {
        notificationManager.run {
            setPlayer(exoPlayer)
            setPriority(NotificationCompat.PRIORITY_MAX)
            setSmallIcon(R.drawable.video_icon)
            setUseRewindAction(false)
            setUseFastForwardAction(false)
        }
    }

    @OptIn(UnstableApi::class)
    fun closeNotificationChannel() {
        exoPlayer.pause()
        notificationManager.run { setPlayer(null) }
    }
}
