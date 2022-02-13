package ru.barinov.jokesapplication.ui

import android.app.Application
import org.koin.android.ext.koin.*
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.barinov.jokesapplication.core.appModule

class App: Application() {

    override fun onCreate() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule))
        }
        super.onCreate()
    }
}