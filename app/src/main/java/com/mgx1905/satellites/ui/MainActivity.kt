package com.mgx1905.satellites.ui

import android.os.Bundle
import androidx.fragment.app.commit
import com.mgx1905.satellites.R
import com.mgx1905.satellites.base.ui.BaseActivity
import com.mgx1905.satellites.ui.list.SatellitesListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentFragmentId = R.id.container

        supportFragmentManager.commit {
            add(R.id.container, SatellitesListFragment.newInstance())
        }
    }
}