package com.mgx1905.satellites.base.di

import android.app.Application
import com.mgx1905.satellites.base.container.AppContainer
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by mgx1905 on 26.12.2021
 */

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppContainer.init(this)
    }
}