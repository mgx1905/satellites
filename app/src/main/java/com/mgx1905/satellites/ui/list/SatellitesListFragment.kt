package com.mgx1905.satellites.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mgx1905.satellites.R
import com.mgx1905.satellites.base.ui.BaseFragment
import com.mgx1905.satellites.databinding.FragmentSatellitesListBinding
import com.mgx1905.satellites.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by mgx1905 on 26.12.2021
 */

@AndroidEntryPoint
class SatellitesListFragment : BaseFragment(R.layout.fragment_satellites_list) {

    companion object {
        fun newInstance() = SatellitesListFragment()
    }

    private val binding by viewBinding(FragmentSatellitesListBinding::bind)

    private val viewModel: SatellitesListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        binding.textView.text = "Hi World!"
    }
}