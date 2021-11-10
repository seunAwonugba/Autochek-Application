package com.example.autochekapplication.dataclass.cars

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stats(
    val appViewCount: Int?,
    val appViewerCount: Int?,
    val interestCount: Int?,
    val processedLoanCount: Int?,
    val testDriveCount: Int?,
    val webViewCount: Int?,
    val webViewerCount: Int?
) : Parcelable