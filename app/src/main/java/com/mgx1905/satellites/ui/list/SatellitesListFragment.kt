package com.mgx1905.satellites.ui.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.mgx1905.satellites.R
import com.mgx1905.satellites.base.common.Resource
import com.mgx1905.satellites.base.listener.DelayedOnQueryTextListener
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
        listeners()
        observers()
    }

    private fun initUI() {
        binding.recyclerView.apply {
            adapter = satellitesListAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun listeners() {
        binding.searchView.setOnQueryTextListener(object : DelayedOnQueryTextListener() {
            override fun onDelayerQueryTextChange(query: String?) {
                viewModel.filterList(query ?: "")
            }
        })
    }

    private fun observers() {
        with(viewModel) {
            satellitesListObservable.onEach {
                binding.progressBar.isVisible = it is Resource.Loading
                when (it) {
                    is Resource.Success -> {
                        satellitesListAdapter.submitList(it.data)
                    }

                    is Resource.Failure -> {
                        showAlert(message = "Failure!")
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }
}