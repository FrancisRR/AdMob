package com.francis.admob

import android.util.Log

object UiUtils {

    internal fun appErrorLog(TAG: String?, msg: String?) {
        Log.e("$TAG", "$msg")
    }

    internal fun appLog(TAG: String?, msg: String?) {
        Log.v("$TAG", "$msg")
    }
}