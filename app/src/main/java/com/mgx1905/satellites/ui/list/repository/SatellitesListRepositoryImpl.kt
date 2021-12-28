package com.mgx1905.satellites.ui.list.repository

import com.google.gson.Gson
import com.mgx1905.satellites.R
import com.mgx1905.satellites.base.common.ApiResult
import com.mgx1905.satellites.data.Satellite
import com.mgx1905.satellites.utils.JsonHelper
import com.mgx1905.satellites.utils.getString
import javax.inject.Inject

/**
 * Created by mgx1905 on 26.12.2021
 */

class SatellitesListRepositoryImpl @Inject constructor() : SatellitesListRepository {
    override suspend fun getSatellites(): ApiResult<Array<Satellite>> {
        val data = JsonHelper.readFromAsset(SATELLITE_LIST)
        val response = Gson().fromJson(data, Array<Satellite>::class.java) ?: return ApiResult.Failure(getString(R.string.parse_error))

        return ApiResult.Success(response)
    }

    companion object {
        private const val SATELLITE_LIST = "satellite-list"
    }
}