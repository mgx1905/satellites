package com.mgx1905.satellites.ui.detail.repository

import com.google.gson.Gson
import com.mgx1905.satellites.R
import com.mgx1905.satellites.base.common.ApiResult
import com.mgx1905.satellites.data.Position
import com.mgx1905.satellites.data.SatelliteDetail
import com.mgx1905.satellites.data.SatellitePositions
import com.mgx1905.satellites.utils.JsonHelper
import com.mgx1905.satellites.utils.getString
import javax.inject.Inject

/**
 * Created by mgx1905 on 28.12.2021
 */

class SatelliteDetailRepositoryImpl @Inject constructor() : SatelliteDetailRepository {
    override suspend fun getSatelliteDetail(id: Int): ApiResult<SatelliteDetail> {
        val data = JsonHelper.readFromAsset(SATELLITE_DETAIL)
        val response = Gson().fromJson(data, Array<SatelliteDetail>::class.java) ?: return ApiResult.Failure(getString(R.string.parse_error))

        val selectedSatellite = response.find { it.id == id }

        return if (selectedSatellite != null) {
            ApiResult.Success(selectedSatellite)
        } else {
            ApiResult.Failure(getString(R.string.id_not_founded))
        }
    }

    override suspend fun getSatellitePositions(id: Int): ApiResult<MutableList<Position>> {
        val data = JsonHelper.readFromAsset(SATELLITE_POSITIONS)
        val response = Gson().fromJson(data, SatellitePositions::class.java) ?: return ApiResult.Failure(getString(R.string.parse_error))

        val selectedSatellitePositions = response.list.find { it.id == id.toString() }

        return if (selectedSatellitePositions != null) {
            ApiResult.Success(selectedSatellitePositions.positions)
        } else {
            ApiResult.Failure(getString(R.string.id_not_founded))
        }
    }

    companion object {
        private const val SATELLITE_DETAIL = "satellite-detail"
        private const val SATELLITE_POSITIONS = "positions"
    }
}