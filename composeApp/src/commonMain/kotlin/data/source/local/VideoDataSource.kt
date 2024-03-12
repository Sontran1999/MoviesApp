package data.source.local

import LocalVideoDataSource
import data.source.local.model.VideoEntity

class VideoDataSource(private val localVideoDataSource: LocalVideoDataSource) {
    suspend fun getLocalVideos(): List<VideoEntity>? = localVideoDataSource.getVideoList()
}