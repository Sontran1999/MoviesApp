package org.example.project

import android.app.Application
import di.appModule
import di.localDataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApp)
            modules(
                appModule(), localDataSourceModule()
            )
        }
    }
}