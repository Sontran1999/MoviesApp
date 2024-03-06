package domain.usecase

import data.model.VideosList
import domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import util.Resource

class GetMovies(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): Flow<Resource<VideosList>> {
        return movieRepository.getMovieList()
    }
}