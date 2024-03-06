package di

import data.constant.NetworkConstant
import data.repository.MovieRepositoryImpl
import data.source.remote.MovieApiService
import domain.repository.MovieRepository
import domain.usecase.GetMovies
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import platformModule


fun appModule() = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
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
}
