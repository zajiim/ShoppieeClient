package com.example.shoppieeclient

import android.app.Application
import com.example.shoppieeclient.data.di.dataModule
import com.example.shoppieeclient.domain.di.domainModule
import com.example.shoppieeclient.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShoppieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShoppieApplication)
//            modules(appModule)
            modules(listOf(
                dataModule,
                domainModule,
                presentationModule
            ))
        }
    }
}