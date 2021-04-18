package com.jinhanexample.holiday

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Holiday(
    val dateKind: String = "",
    val dateName: String = "",
    val isHoliday: String = "",
    val locdate: String = "",
) : Parcelable