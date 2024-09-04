package com.example.pomodoro_22

import android.app.Application
import com.example.pomodoro_22.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}
