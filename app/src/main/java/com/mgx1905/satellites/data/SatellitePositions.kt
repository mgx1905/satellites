package com.mgx1905.satellites.data

/**
 * Created by mgx1905 on 26.12.2021
 */

data class SatellitePositions(
    val list: MutableList<SatellitesPositionInfo>,
)

data class SatellitesPositionInfo(
    val id: String,
    val positions: MutableList<Position>,
)

data class Position(
    val posX: Double,
    val posY: Double,
)