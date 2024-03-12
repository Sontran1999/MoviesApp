package domain.repository

import domain.model.VideoMedia
import kotlinx.coroutines.flow.Flow
import util.Resource

interface VideoOfflineRepository {
    suspend fun getVideoList(): Flow<Resource<List<VideoMedia>>>
}