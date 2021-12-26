package com.mgx1905.satellites.base.common

/**
 * Created by mgx1905 on 26.12.2021
 */

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure(val message: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}