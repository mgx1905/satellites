package com.mgx1905.satellites.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgx1905.satellites.base.common.ApiResult
import com.mgx1905.satellites.base.common.Resource
import com.mgx1905.satellites.data.Satellites
import com.mgx1905.satellites.ui.list.usecases.SatellitesListUseCase
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

    private val _satellitesList = MutableStateFlow<Resource<Array<Satellites>>>(Resource.Loading)
    val satellitesList = _satellitesList as StateFlow<Resource<Array<Satellites>>>

    fun getSatellitesList() {
        _satellitesList.value = Resource.Loading
        satellitesListUseCase.execute(Any()).onEach {
            when (it) {
                is ApiResult.Success -> {
                    _satellitesList.value = Resource.Success(it.response ?: emptyArray())
                }
                is ApiResult.Failure -> {
                    _satellitesList.value = Resource.Failure(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}