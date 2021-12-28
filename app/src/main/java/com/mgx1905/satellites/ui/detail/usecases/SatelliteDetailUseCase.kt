package com.mgx1905.satellites.ui.detail.usecases

import com.mgx1905.satellites.base.common.ApiResult
import com.mgx1905.satellites.base.common.BaseUseCase
import com.mgx1905.satellites.data.SatelliteDetail
import com.mgx1905.satellites.ui.detail.repository.SatelliteDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by mgx1905 on 28.12.2021
 */

class SatelliteDetailUseCase @Inject constructor(private val satelliteDetailRepository: SatelliteDetailRepository) :
    BaseUseCase<SatelliteDetailUseCase.SatelliteDetailParams, ApiResult<SatelliteDetail>>() {

    override fun execute(input: SatelliteDetailParams): Flow<ApiResult<SatelliteDetail>> = flow {
        emit(
            satelliteDetailRepository.getSatelliteDetail(id = input.id)
        )
    }

    data class SatelliteDetailParams(
        val id: Int
    )
}