package com.fups.examplecaseandroid.tools

import android.os.Handler
import android.os.Looper
import android.util.Log

fun log(string: String) {
    Handler(Looper.getMainLooper()).post {
        Log.d("Fubs", string)
    }
}