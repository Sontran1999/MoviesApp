package domain.usecase

import domain.model.VideoMedia
import domain.repository.VideosRepository
import kotlinx.coroutines.flow.Flow
import util.Resource

class GetVideosOnline(private val videosRepository: VideosRepository) {
    suspend operator fun invoke(): Flow<Resource<List<VideoMedia>>> {
        return videosRepository.getVideoList()
    }
}