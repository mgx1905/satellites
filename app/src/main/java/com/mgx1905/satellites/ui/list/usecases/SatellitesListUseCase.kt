package com.mgx1905.satellites.ui.list.usecases

import com.mgx1905.satellites.base.common.ApiResult
import com.mgx1905.satellites.base.common.BaseUseCase
import com.mgx1905.satellites.data.Satellites
import com.mgx1905.satellites.ui.list.repository.SatellitesListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by mgx1905 on 26.12.2021
 */

class SatellitesListUseCase @Inject constructor(private val satellitesListRepository: SatellitesListRepository) :
    BaseUseCase<Any, ApiResult<Array<Satellites>>>() {
    override fun execute(input: Any): Flow<ApiResult<Array<Satellites>>> = flow {
        emit(
            satellitesListRepository.getSatellites()
        )
    }
}