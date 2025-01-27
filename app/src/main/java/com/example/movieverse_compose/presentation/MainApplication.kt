package com.example.movieverse_compose.presentation

import android.app.Application
import com.example.movieverse_compose.hilt.appModule
import com.example.movieverse_compose.hilt.viewModelModule
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