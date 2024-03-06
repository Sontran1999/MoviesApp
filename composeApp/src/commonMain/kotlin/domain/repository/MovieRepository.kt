package domain.repository

import data.model.VideosList
import kotlinx.coroutines.flow.Flow
import util.Resource

interface MovieRepository {
    suspend fun getMovieList(): Flow<Resource<VideosList>>
}