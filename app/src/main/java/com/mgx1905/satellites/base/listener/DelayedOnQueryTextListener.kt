package com.mgx1905.satellites.base.listener

import android.os.Handler
import android.os.Looper
import android.widget.SearchView

/**
 * Created by mgx1905 on 28.12.2021
 */

abstract class DelayedOnQueryTextListener : SearchView.OnQueryTextListener {
    private val handler: Handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null

    override fun onQueryTextSubmit(s: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(s: String?): Boolean {
        runnable?.let { handler.removeCallbacks(it) }
        runnable = Runnable { onDelayerQueryTextChange(s) }
        runnable?.let { handler.postDelayed(it, 300) }
        return true
    }

    abstract fun onDelayerQueryTextChange(query: String?)
}