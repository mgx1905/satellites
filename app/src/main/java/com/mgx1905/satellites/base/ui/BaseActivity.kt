package com.mgx1905.satellites.base.ui

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import com.mgx1905.satellites.R
import com.mgx1905.satellites.base.listener.NavigationListener

/**
 * Created by mgx1905 on 26.12.2021
 */

abstract class BaseActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId), NavigationListener {

    @IdRes
    protected var currentFragmentId = 0

    override fun navigate(fragment: Fragment, tag: String?, isAdding: Boolean) {
        open(isAdding, currentFragmentId, fragment, tag)
    }

    override fun navigateBack(tag: String) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED).not()) return
        if (isFinishing) return
        supportFragmentManager.apply {
            if (backStackEntryCount > 0) {
                popBackStack(tag, 0)
            }
        }
    }

    override fun onBackPressed() {
        supportFragmentManager.apply {
            if (backStackEntryCount > 0) {
                popBackStack()
                return
            }
            super.finish()
        }
    }

    private fun open(isAdding: Boolean = false, @IdRes containerId: Int, fragment: Fragment, tag: String? = null) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val currentFragment = supportFragmentManager.findFragmentById(currentFragmentId)
            if (currentFragment != null) {
                setCustomAnimations(
                    R.anim.fragment_slide_right_enter,
                    R.anim.fragment_slide_left_exit,
                    R.anim.fragment_slide_left_enter,
                    R.anim.fragment_slide_right_exit
                )
                hide(currentFragment)
            }
            if (isAdding) {
                add(containerId, fragment, tag)
            } else {
                replace(containerId, fragment, tag)
            }
            show(fragment)
            addToBackStack(tag)
        }
    }
}