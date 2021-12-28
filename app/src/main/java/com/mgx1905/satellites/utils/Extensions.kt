package com.mgx1905.satellites.utils

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

/**
 * Created by mgx1905 on 27.12.2021
 */

fun postDelay(delayInMillis: Long, block: () -> Unit) = Handler(Looper.getMainLooper()).postDelayed(block, delayInMillis)

fun CoroutineScope.launchPeriodicAsync(repeatMillis: Long, isActive: Boolean? = false, action: () -> Unit) = this.async {
    while (isActive == true) {
        action()
        delay(repeatMillis)
    }
}