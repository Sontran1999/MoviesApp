package domain.usecase

import domain.model.VideoMedia
import domain.repository.VideoOfflineRepository
import kotlinx.coroutines.flow.Flow
import util.Resource

class GetVideosOffline(private val videoOfflineRepository: VideoOfflineRepository) {
    suspend operator fun invoke(): Flow<Resource<List<VideoMedia>>> {
        return videoOfflineRepository.getVideoList()
    }
}