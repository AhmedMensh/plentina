package com.android.plentinatask.ui

import android.app.Application
import com.android.plentinatask.BuildConfig
import com.android.plentinatask.network.Network

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //todo base url should be in the local.properties file
        Network.init("https://simple-books-api.glitch.me/", BuildConfig.DEBUG)
    }
}