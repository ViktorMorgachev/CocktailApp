package com.beeline.demo.cocktailapp

import android.app.Application
import com.beeline.demo.cocktailapp.di.modules.mainModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            androidLogger()
            modules(listOf(mainModules()))
        }
    }
}