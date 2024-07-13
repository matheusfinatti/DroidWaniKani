package com.mfinatti.wanikanisimple

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DroidWaniKani : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(Consts.TAG, "Application initialized")
    }
}
