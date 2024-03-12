package common

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession

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
}
