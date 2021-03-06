package com.beeline.demo.cocktailapp

import android.app.Application
import com.beeline.demo.cocktailapp.data.network.networkModule
import com.beeline.demo.cocktailapp.di.modules.cocktailModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            androidLogger()
            modules(listOf(cocktailModule, networkModule))
        }
        Timber.plant(DebugTree()) /*else {
            *//*Timber.plant(ReleaseTree())*//*
        }*/
    }
}