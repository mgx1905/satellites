package com.mgx1905.satellites.data

import kotlinx.serialization.SerialName

/**
 * Created by mgx1905 on 26.12.2021
 */

data class SatelliteDetail(
    val id: String,
    @SerialName("first_flight")
    val costPerLaunch: Int,
    @SerialName("first_flight")
    val firstFlight: Int,
    val height: Int,
    val mass: Int,
)