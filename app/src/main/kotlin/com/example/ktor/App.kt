package com.example.ktor

import android.app.Application
import com.example.ktor.di.networkModule
import com.example.ktor.prices_list.PricesApi
import com.example.ktor.prices_list.di.pricesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                module { single { PricesApi() } }
            )
        }
    }
}
