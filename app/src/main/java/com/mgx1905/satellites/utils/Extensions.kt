package com.mgx1905.satellites.utils

import android.os.Handler
import android.os.Looper

/**
 * Created by mgx1905 on 27.12.2021
 */

fun postDelay(delayInMillis: Long, block: () -> Unit) = Handler(Looper.getMainLooper()).postDelayed(block, delayInMillis)