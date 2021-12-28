package com.mgx1905.satellites.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.textview.MaterialTextView
import com.mgx1905.satellites.base.container.AppContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

/**
 * Created by mgx1905 on 27.12.2021
 */

const val EMPTY = ""

val context: Context get() = AppContainer.appContext

fun postDelay(delayInMillis: Long, block: () -> Unit) = Handler(Looper.getMainLooper()).postDelayed(block, delayInMillis)

fun getString(@StringRes resId: Int) = context.getString(resId)

fun getDrawable(@DrawableRes resId: Int) = AppCompatResources.getDrawable(context, resId)

fun MaterialTextView.setLeftDrawable(resId: Int) =
    setCompoundDrawablesWithIntrinsicBounds(getDrawable(resId), null, null, null)

fun CoroutineScope.launchPeriodicAsync(repeatMillis: Long, isActive: Boolean? = false, action: () -> Unit) = this.async {
    while (isActive == true) {
        action()
        delay(repeatMillis)
    }
}