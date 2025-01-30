package com.example.movieverse_compose.presentation

import android.app.Application
import com.example.movieverse_compose.koin.appModule
import com.example.movieverse_compose.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule, viewModelModule)
        }
    }

}