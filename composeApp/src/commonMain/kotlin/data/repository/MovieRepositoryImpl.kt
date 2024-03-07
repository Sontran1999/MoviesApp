package data.repository

import data.model.VideosList
import data.source.remote.MovieApiService
import domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import util.Resource

class MovieRepositoryImpl(private val movieApiService: MovieApiService) : MovieRepository {

    override suspend fun getMovieList(): Flow<Resource<VideosList>> {
        return flow {
            emit(Resource.Success(movieApiService.getAllMovies()))
        }
    }
}