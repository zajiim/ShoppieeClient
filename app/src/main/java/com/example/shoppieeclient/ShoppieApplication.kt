package com.example.shoppieeclient

import android.app.Application
import com.cloudinary.android.MediaManager
import com.example.shoppieeclient.data.di.dataModule
import com.example.shoppieeclient.domain.di.domainModule
import com.example.shoppieeclient.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import com.example.shoppieeclient.BuildConfig

class ShoppieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initCloudinary()
        startKoin {
            androidLogger()
            androidContext(this@ShoppieApplication)
//            modules(appModule)
            modules(listOf(
                dataModule,
                domainModule,
                presentationModule
            ))
        }
    }

    private fun initCloudinary() {
        val config = HashMap<String, Any>()
        config["cloud_name"] = BuildConfig.CLOUDINARY_CLOUD_NAME
        config["api_key"] = BuildConfig.CLOUDINARY_API_KEY
        config["api_secret"] = BuildConfig.CLOUDINARY_API_SECRET
        MediaManager.init(this, config)
    }
}