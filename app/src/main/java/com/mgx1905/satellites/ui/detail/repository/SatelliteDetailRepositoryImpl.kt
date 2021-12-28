package com.mgx1905.satellites.ui.detail.repository

import android.content.Context
import com.google.gson.Gson
import com.mgx1905.satellites.base.common.ApiResult
import com.mgx1905.satellites.data.Position
import com.mgx1905.satellites.data.SatelliteDetail
import com.mgx1905.satellites.data.SatellitePositions
import com.mgx1905.satellites.utils.JsonHelper
import javax.inject.Inject

/**
 * Created by mgx1905 on 28.12.2021
 */
class SatelliteDetailRepositoryImpl @Inject constructor(private val context: Context) : SatelliteDetailRepository {
    override suspend fun getSatelliteDetail(id: Int): ApiResult<SatelliteDetail> {
        val data = JsonHelper.readFromAsset(context, "satellite-detail")
        val response = Gson().fromJson(data, Array<SatelliteDetail>::class.java)

        val selectedSatellite = response.find { it.id == id }

        return if (selectedSatellite != null) {
            ApiResult.Success(selectedSatellite)
        } else {
            ApiResult.Failure("Selected id not founded!")
        }
    }

    override suspend fun getSatellitePositions(id: Int): ApiResult<MutableList<Position>> {
        val data = JsonHelper.readFromAsset(context, "positions")
        val response = Gson().fromJson(data, SatellitePositions::class.java)

        val selectedSatellitePositions = response.list.find { it.id == id.toString() }

        return if (selectedSatellitePositions != null) {
            ApiResult.Success(selectedSatellitePositions.positions)
        } else {
            ApiResult.Failure("Selected id not founded!")
        }
    }
}