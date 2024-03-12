package domain.repository

import domain.model.VideoMedia
import kotlinx.coroutines.flow.Flow
import util.Resource

interface VideosRepository {
    suspend fun getVideoList(): Flow<Resource<List<VideoMedia>>>
}