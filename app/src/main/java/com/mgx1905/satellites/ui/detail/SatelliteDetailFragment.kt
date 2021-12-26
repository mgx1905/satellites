package com.mgx1905.satellites.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.mgx1905.satellites.R
import com.mgx1905.satellites.base.ui.BaseFragment
import com.mgx1905.satellites.data.Satellite
import com.mgx1905.satellites.databinding.FragmentSatellitesDetailBinding
import com.mgx1905.satellites.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by mgx1905 on 27.12.2021
 */

@AndroidEntryPoint
class SatelliteDetailFragment : BaseFragment(R.layout.fragment_satellites_detail) {

    companion object {
        private const val TAG_SATELLITE = "tag_satellite"

        fun newInstance(satellite: Satellite) = SatelliteDetailFragment().apply {
            arguments = bundleOf(TAG_SATELLITE to satellite)
        }
    }

    private val satellite: Satellite? by lazy { requireArguments().getParcelable(TAG_SATELLITE) }

    private val binding by viewBinding(FragmentSatellitesDetailBinding::bind)

    private val viewModel: SatelliteDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {

    }
}