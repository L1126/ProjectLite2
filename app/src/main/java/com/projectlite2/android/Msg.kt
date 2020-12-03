package com.projectlite2.android

import android.content.Context

class Msg(val content: String, val type: Int) {
    companion object{
        const val TYPE_RECEIVED = 0
        const val TYPE_SENT = 1
    }
}