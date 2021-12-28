package com.mgx1905.satellites.ui.detail.usecases

import com.mgx1905.satellites.base.common.ApiResult
import com.mgx1905.satellites.base.common.BaseUseCase
import com.mgx1905.satellites.data.Position
import com.mgx1905.satellites.ui.detail.repository.SatelliteDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by mgx1905 on 28.12.2021
 */

class SatellitePositionUseCase @Inject constructor(private val satelliteDetailRepository: SatelliteDetailRepository) :
    BaseUseCase<SatellitePositionUseCase.SatellitePositionsParams, ApiResult<MutableList<Position>>>() {

    override fun execute(input: SatellitePositionsParams): Flow<ApiResult<MutableList<Position>>> = flow {
        emit(
            satelliteDetailRepository.getSatellitePositions(id = input.id)
        )
    }

    data class SatellitePositionsParams(
        val id: Int
    )
}