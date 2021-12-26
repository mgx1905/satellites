package com.mgx1905.satellites.base.data

import kotlinx.coroutines.flow.Flow

/**
 * Created by mgx1905 on 26.12.2021
 */

abstract class BaseUseCase<In, Out> {

    abstract fun execute(input: In): Flow<Out>
}