package com.mgx1905.satellites.ui.list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mgx1905.satellites.R
import com.mgx1905.satellites.base.common.Resource
import com.mgx1905.satellites.base.ui.BaseFragment
import com.mgx1905.satellites.databinding.FragmentSatellitesListBinding
import com.mgx1905.satellites.ui.detail.SatelliteDetailFragment
import com.mgx1905.satellites.ui.list.adapter.SatellitesListAdapter
import com.mgx1905.satellites.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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

    private val satellitesListAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SatellitesListAdapter {
            navigationListener.navigate(SatelliteDetailFragment.newInstance(it))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        observers()
    }

    private fun initUI() {
        binding.recyclerView.adapter = satellitesListAdapter
    }

    private fun observers() {
        with(viewModel) {
            satellitesList.onEach {
                when (it) {
                    is Resource.Success -> {
                        if (it.data.isNullOrEmpty().not()) {
                            satellitesListAdapter.submitList(it.data.toList())
                        } else {
                            showAlert(message = "Empty or null list!")
                        }
                    }

                    is Resource.Failure -> {
                        showAlert(message = "Failure!")
                    }

                    is Resource.Loading -> {
                        Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }
}