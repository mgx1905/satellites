package com.mgx1905.satellites.base.common

/**
 * Created by mgx1905 on 26.12.2021
 */

sealed class ApiResult<out T> {
    data class Success<T>(val response: T?) : ApiResult<T>()
    data class Failure(val error: String) : ApiResult<Nothing>()
}