package com.mgx1905.satellites.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mgx1905.satellites.R
import com.mgx1905.satellites.base.common.Resource
import com.mgx1905.satellites.base.ui.BaseFragment
import com.mgx1905.satellites.data.Satellite
import com.mgx1905.satellites.data.SatelliteDetail
import com.mgx1905.satellites.databinding.FragmentSatellitesDetailBinding
import com.mgx1905.satellites.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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

        observers()
        viewModel.getSatelliteDetail(satellite?.id ?: 0, isActive = satellite?.active)
    }

    private fun observers() {
        with(viewModel) {
            satelliteDetailObservable.onEach {
                binding.progressBar.isVisible = it is Resource.Loading
                when (it) {
                    is Resource.Success -> {
                        binding.constraint.isVisible = true
                        setDetailInfo(it.data)
                    }
                    is Resource.Failure -> {
                        showAlert(message = "Failure!", buttonText = "Close") {
                            navigationListener.onBackPressed()
                        }
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            satellitePositionObservable.onEach {
                when (it) {
                    is Resource.Success -> {
                        binding.tvLastPosition.text = "(${it.data.posX}, ${it.data.posY})"
                    }
                    is Resource.Failure -> {
                        showAlert(message = "Failure!")
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    private fun setDetailInfo(data: SatelliteDetail) {
        data.run {
            with(binding) {
                tvShipName.text = satellite?.name
                tvFirstFlight.text = firstFlight
                tvHeightAndMass.text = "${height}/${mass}"
                tvCost.text = costPerLaunch.toString()
            }
        }
    }
}