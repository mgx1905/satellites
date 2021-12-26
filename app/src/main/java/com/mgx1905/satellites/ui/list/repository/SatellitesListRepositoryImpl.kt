package com.mgx1905.satellites.ui.list.repository

import android.content.Context
import com.google.gson.Gson
import com.mgx1905.satellites.base.common.ApiResult
import com.mgx1905.satellites.data.Satellite
import com.mgx1905.satellites.utils.JsonHelper
import javax.inject.Inject

/**
 * Created by mgx1905 on 26.12.2021
 */

class SatellitesListRepositoryImpl @Inject constructor(private val context: Context) : SatellitesListRepository {
    override suspend fun getSatellites(): ApiResult<Array<Satellite>> {
        val data = JsonHelper.readFromAsset(context, "satellite-list")
        val response = Gson().fromJson(data, Array<Satellite>::class.java)

        return if (response != null) {
            ApiResult.Success(response)
        } else {
            ApiResult.Failure("Parse error!")
        }
    }
}