package com.mgx1905.satellites.data

import com.google.gson.annotations.SerializedName

/**
 * Created by mgx1905 on 26.12.2021
 */

data class SatelliteDetail(
    val id: Int,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Double,
    @SerializedName("first_flight")
    val firstFlight: String,
    val height: Int,
    val mass: Int,
)