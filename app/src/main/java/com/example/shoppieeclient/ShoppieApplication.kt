package com.example.shoppieeclient

import android.app.Application
import com.example.shoppieeclient.common.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShoppieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShoppieApplication)
            modules(appModule)
        }
    }
}