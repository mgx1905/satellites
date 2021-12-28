package com.mgx1905.satellites.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgx1905.satellites.base.common.ApiResult
import com.mgx1905.satellites.base.common.Resource
import com.mgx1905.satellites.data.Satellite
import com.mgx1905.satellites.ui.list.usecases.SatellitesListUseCase
import com.mgx1905.satellites.utils.postDelay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by mgx1905 on 26.12.2021
 */

@HiltViewModel
class SatellitesListViewModel @Inject constructor(private val satellitesListUseCase: SatellitesListUseCase) : ViewModel() {

    private var satellitesList = mutableListOf<Satellite>()

    private val _satellitesListObservable = MutableStateFlow<Resource<MutableList<Satellite>>>(Resource.Loading)
    val satellitesListObservable = _satellitesListObservable as StateFlow<Resource<MutableList<Satellite>>>

    init {
        getSatellitesList()
    }

    private fun getSatellitesList() {
        _satellitesListObservable.value = Resource.Loading

        satellitesListUseCase.execute(Any()).onEach {
            when (it) {
                is ApiResult.Success -> {
                    postDelay(POST_DELAY) {
                        satellitesList = it.response?.toMutableList() ?: mutableListOf()
                        _satellitesListObservable.value = Resource.Success(satellitesList)
                    }
                }
                is ApiResult.Failure -> {
                    _satellitesListObservable.value = Resource.Failure(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun filterList(query: String) {
        if (query.isEmpty()) {
            _satellitesListObservable.value = Resource.Success(satellitesList)
        } else {
            val filteredList = satellitesList.filter { it.name.contains(query, true) }.toMutableList()
            _satellitesListObservable.value = Resource.Success(filteredList)
        }
    }

    companion object {
        private const val POST_DELAY = 1500L
    }
}