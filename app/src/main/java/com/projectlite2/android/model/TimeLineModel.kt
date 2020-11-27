package com.projectlite2.android.model

import android.os.Parcel
import android.os.Parcelable

class TimeLineModel(
        var message: String,
        var date: String,
        var status: OrderStatus
) {
}