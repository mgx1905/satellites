package com.mgx1905.satellites.base.container

import android.app.Application
import android.content.Context

/**
 * Created by mgx1905 on 29.12.2021
 */

object AppContainer {
    lateinit var appContext: Context
        private set

    fun init(application: Application) = apply {
        appContext = application.applicationContext
    }
}