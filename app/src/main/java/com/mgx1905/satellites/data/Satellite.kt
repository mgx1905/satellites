package com.mgx1905.satellites.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by mgx1905 on 26.12.2021
 */

@Parcelize
data class Satellite(
    val id: Int,
    val active: Boolean,
    val name: String,
) : Parcelable