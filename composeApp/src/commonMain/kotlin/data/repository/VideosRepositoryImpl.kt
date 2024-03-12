package data.repository

import data.mapper.toVideoMedia
import data.source.remote.VideoApiService
import domain.model.VideoMedia
import domain.repository.VideosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import util.Resource

class VideosRepositoryImpl(private val videoApiService: VideoApiService) : VideosRepository {

    override suspend fun getVideoList(): Flow<Resource<List<VideoMedia>>> {
        return flow {
            emit(Resource.Success(videoApiService.getAllVideos().videos?.map { it.toVideoMedia() }))
        }
    }
}