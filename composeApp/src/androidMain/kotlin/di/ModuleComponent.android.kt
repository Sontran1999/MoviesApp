package di

import LocalVideoDataSource
import org.koin.dsl.module

actual fun localDataSourceModule() = module {
    single {
        LocalVideoDataSource(get())
    }
}