import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.ui.PlayerView
import common.ExoPlayerManager
import data.source.local.model.VideoEntity

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Composable
actual fun VideoPlayer(url: String) {
    val context = LocalContext.current
    val exoPlayerManager = ExoPlayerManager.getInstance(context)
    val exoplayer = exoPlayerManager.exoPlayer.apply {
        setMediaItem(MediaItem.fromUri(Uri.parse(url)))
        prepare()
        play()
    }

    exoPlayerManager.session.player = exoplayer

    AndroidView(
        factory = {
            PlayerView(it).apply {
                player = exoplayer
            }
        }, modifier = Modifier.fillMaxSize()
    )
}

actual class LocalVideoDataSource(private val context: Context) {
    actual suspend fun getVideoList(): List<VideoEntity>? {
        return getVideoFromMediaStore(context)
    }

    @SuppressLint("Range")
    fun getVideoFromMediaStore(context: Context): List<VideoEntity>? {
        val result = mutableListOf<VideoEntity>()
        val projection = arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.ARTIST,
            MediaStore.Video.Media.DURATION,
        )
        val sortOrder = "${MediaStore.Video.Media.DATE_ADDED} DESC"
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Video.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        }
        context.contentResolver.query(
            collection, projection, null, null, sortOrder
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
                val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media._ID))
                val imageUri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                result.add(
                    VideoEntity(
                        id = id,
                        name = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME)),
                        duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DURATION)) / 1000,
                        url = cursor.getString(dataColumn),
                        picture = imageUri.toString()
                    )
                )
            }
            return result
        }
        return null
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
actual fun getVideoThumbnail(videoUri: String): ImageBitmap? {
    val context = LocalContext.current
    return context.contentResolver.loadThumbnail(
        Uri.parse(videoUri), Size(640, 480), null
    ).asImageBitmap()
}