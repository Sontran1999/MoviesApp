package di

import LocalVideoDataSource
import NetworkManager
import org.koin.dsl.module

actual fun localDataSourceModule() = module {
    single {
        LocalVideoDataSource(get())
    }

    single {
        NetworkManager(get())
    }
}