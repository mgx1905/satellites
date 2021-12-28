package com.mgx1905.satellites.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgx1905.satellites.base.common.ApiResult
import com.mgx1905.satellites.base.common.Resource
import com.mgx1905.satellites.data.Position
import com.mgx1905.satellites.data.SatelliteDetail
import com.mgx1905.satellites.ui.detail.usecases.SatelliteDetailUseCase
import com.mgx1905.satellites.ui.detail.usecases.SatellitePositionUseCase
import com.mgx1905.satellites.utils.launchPeriodicAsync
import com.mgx1905.satellites.utils.postDelay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by mgx1905 on 27.12.2021
 */

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val satelliteDetailUseCase: SatelliteDetailUseCase,
    private val satellitePositionUseCase: SatellitePositionUseCase,
) : ViewModel() {

    private val _satelliteDetailObservable = MutableStateFlow<Resource<SatelliteDetail>>(Resource.Loading)
    val satelliteDetailObservable = _satelliteDetailObservable as StateFlow<Resource<SatelliteDetail>>

    private val _satellitePositionObservable = MutableStateFlow<Resource<Position>>(Resource.Loading)
    val satellitePositionObservable = _satellitePositionObservable as StateFlow<Resource<Position>>

    fun getSatelliteDetail(id: Int, isActive: Boolean? = false) {
        _satelliteDetailObservable.value = Resource.Loading

        satelliteDetailUseCase.execute(SatelliteDetailUseCase.SatelliteDetailParams(id)).onEach {
            when (it) {
                is ApiResult.Success -> {
                    postDelay(1500) {
                        _satelliteDetailObservable.value = Resource.Success(it.response!!)
                        getCurrentPosition(id, isActive)
                    }
                }
                is ApiResult.Failure -> {
                    _satelliteDetailObservable.value = Resource.Failure(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCurrentPosition(id: Int, isActive: Boolean? = false) {
        satellitePositionUseCase.execute(SatellitePositionUseCase.SatellitePositionsParams(id)).onEach {
            when (it) {
                is ApiResult.Success -> {
                    it.response?.let { positionList ->
                        if (isActive == false) {
                            _satellitePositionObservable.value = Resource.Success(positionList[0])
                        } else {
                            handlePosition(positionList, isActive)
                        }
                    }
                }
                is ApiResult.Failure -> {
                    _satellitePositionObservable.value = Resource.Failure(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun handlePosition(response: MutableList<Position>, isActive: Boolean?) {
        var currentCount = 0
        viewModelScope.launchPeriodicAsync(3000, isActive) {
            _satellitePositionObservable.value = Resource.Success(response[currentCount % 3])
            currentCount++
            Log.i("timer", "Timer is running!")
        }
    }
}