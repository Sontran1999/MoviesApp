package di

import data.repository.MovieRepositoryImpl
import data.source.remote.MovieApiService
import domain.repository.MovieRepository
import domain.usecase.GetMovies
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import presentation.ui.home.HomeViewModel
import presentation.ui.online.OnlineMovieViewModel


fun appModule() = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }
    single {
        MovieApiService(get())
    }
    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }

    single<GetMovies> {
        GetMovies(get())
    }

    single { HomeViewModel() }

    single { OnlineMovieViewModel(get()) }
}
