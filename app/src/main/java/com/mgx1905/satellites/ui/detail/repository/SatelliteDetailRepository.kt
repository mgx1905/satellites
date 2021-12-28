package com.mgx1905.satellites.ui.detail.repository

import com.mgx1905.satellites.base.common.ApiResult
import com.mgx1905.satellites.data.Position
import com.mgx1905.satellites.data.SatelliteDetail

/**
 * Created by mgx1905 on 28.12.2021
 */

interface SatelliteDetailRepository {

    suspend fun getSatelliteDetail(id: Int): ApiResult<SatelliteDetail>

    suspend fun getSatellitePositions(id: Int): ApiResult<MutableList<Position>>
}