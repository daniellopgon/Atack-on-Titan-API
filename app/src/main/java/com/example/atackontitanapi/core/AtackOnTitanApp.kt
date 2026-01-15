package com.example.atackontitanapi.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import com.example.atackontitanapi.core.di.AppModule

class AtackOnTitanApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AtackOnTitanApp)
            modules(AppModule().module)
        }
    }
}
