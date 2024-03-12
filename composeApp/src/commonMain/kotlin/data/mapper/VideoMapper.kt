package data.mapper

import data.source.local.model.VideoEntity
import data.source.remote.model.Video
import domain.model.VideoMedia

fun VideoEntity.toVideoMedia(): VideoMedia {
    return VideoMedia(id = id, name = name, url = url, duration = duration, picture = picture)
}

fun Video.toVideoMedia(): VideoMedia {
    return VideoMedia(
        id = id,
        name = user.name,
        url = videoFiles[0].link,
        duration = duration,
        picture = videoPictures[0].picture
    )
}