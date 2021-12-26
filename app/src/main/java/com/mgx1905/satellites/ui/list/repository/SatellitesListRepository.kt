package com.mgx1905.satellites.ui.list.repository

import com.mgx1905.satellites.base.common.ApiResult
import com.mgx1905.satellites.data.Satellites

/**
 * Created by mgx1905 on 26.12.2021
 */

interface SatellitesListRepository {

    suspend fun getSatellites(): ApiResult<Array<Satellites>>
}