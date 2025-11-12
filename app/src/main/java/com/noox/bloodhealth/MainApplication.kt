package com.noox.bloodhealth

import android.app.Application
import com.noox.bloodhealth.core.di.coreModule
import com.noox.bloodhealth.features.bloodpressure.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                coreModule,
                homeModule
            )
        }
    }
}