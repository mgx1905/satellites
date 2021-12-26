package com.mgx1905.satellites.base.listener

import androidx.fragment.app.Fragment

/**
 * Created by mgx1905 on 26.12.2021
 */

interface NavigationListener {

    fun navigate(fragment: Fragment, tag: String? = null, isAdding: Boolean = false)

    fun onBackPressed()

    fun navigateBack(tag: String)

}