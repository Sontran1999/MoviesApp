package data.repository

import data.mapper.toVideoMedia
import data.source.local.VideoDataSource
import domain.model.VideoMedia
import domain.repository.VideoOfflineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import util.Resource

class VideosOfflineRepositoryImpl(private val videoDataSource: VideoDataSource) :
    VideoOfflineRepository {
    override suspend fun getVideoList(): Flow<Resource<List<VideoMedia>>> {
        return flow {
            emit(Resource.Success(videoDataSource.getLocalVideos()?.map { it.toVideoMedia() }))
        }
    }
}