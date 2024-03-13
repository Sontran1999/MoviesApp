package di

import data.repository.VideosOfflineRepositoryImpl
import data.repository.VideosRepositoryImpl
import data.source.local.VideoDataSource
import data.source.remote.VideoApiService
import domain.repository.VideoOfflineRepository
import domain.repository.VideosRepository
import domain.usecase.GetVideosOffline
import domain.usecase.GetVideosOnline
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module
import presentation.ui.home.HomeViewModel
import presentation.ui.offline.OfflineVideosViewModel
import presentation.ui.online.OnlineVideoViewModel


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

    single<VideoApiService> {
        VideoApiService(get())
    }

    single<VideoDataSource> {
        VideoDataSource(get())
    }
    single<VideosRepository> {
        VideosRepositoryImpl(get())
    }

    single<VideoOfflineRepository> {
        VideosOfflineRepositoryImpl(get())
    }

    single<GetVideosOnline> {
        GetVideosOnline(get())
    }

    single<GetVideosOffline> {
        GetVideosOffline(get())
    }

    single { HomeViewModel() }

    single { OnlineVideoViewModel(get(), get()) }

    single { OfflineVideosViewModel(get()) }
}

expect fun localDataSourceModule(): Module