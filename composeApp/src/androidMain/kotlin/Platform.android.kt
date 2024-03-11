import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.ui.PlayerView
import org.example.project.common.ExoPlayerManager

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

@Composable
actual fun getVideoFromMediaStore(): String? {
    val context = LocalContext.current
    val projection = arrayOf(MediaStore.Video.Media._ID, MediaStore.Video.Media.DATA)
    val sortOrder = "${MediaStore.Video.Media.DATE_ADDED} DESC"

    context.contentResolver.query(
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
        projection,
        null,
        null,
        sortOrder
    )?.use { cursor ->
        while (cursor.moveToNext()) {

        }
        if (cursor.moveToFirst()) {
            val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            return cursor.getString(dataColumn)
        }
    }

    return null
}

//actual class LocalAudioDataSource(val appContext: Context) {
//    private val ALBUM_ART_URL = "content://media/external/audio/albumart"
//    actual suspend fun getAudioList(): List<LocalAudio> {
//        return getAudioList(appContext)
//    }
//    @SuppressLint("Range")
//    fun getAudioList(appContext: Context): List<LocalAudio> {
//        val result = mutableListOf<LocalAudio>()
//        val projections =
//            arrayOf(
//                MediaStore.Audio.Media._ID,
//                MediaStore.Audio.Media.DISPLAY_NAME,
//                MediaStore.Audio.Media.DURATION,
//                MediaStore.Audio.Media.SIZE,
//                MediaStore.Audio.Media.ALBUM_ID,
//                MediaStore.Audio.Media.ARTIST
//            )
//        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
//        } else {
//            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//        }
//        val cursor = appContext.contentResolver.query(
//            collection,
//            projections,
//            null,
//            null,
//            null
//        )
//        cursor?.use {
//            while (it.moveToNext()) {
//                val id = it.getLong(it.getColumnIndex(MediaStore.Audio.Media._ID))
//                val name = it.getString(it.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
//                val duration = it.getInt(it.getColumnIndex(MediaStore.Audio.Media.DURATION))
//                val size = it.getInt(it.getColumnIndex(MediaStore.Audio.Media.SIZE))
//                val albumId = it.getLong(it.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
//                val uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
//                val artis = it.getString(it.getColumnIndex(MediaStore.Audio.Media.ARTIST))
//                val albumArtworkUri = ContentUris.withAppendedId(
//                    Uri.parse(ALBUM_ART_URL),
//                    albumId
//                )
//                result.add(
//                    LocalAudio(
//                        name,
//                        uri.toString(),
//                        albumArtworkUri.toString(),
//                        size,
//                        duration,
//                        artis
//                    )
//                )
//            }
//        }
//        return result
//    }
//}