package xyz.manzodev.sgo48

import android.util.Log

const val TAG = "SGO48"
const val BASE_URL = "https://www.sgo48.vn"

fun Any.logd(tag:String = TAG) {
    if (this is String){
        Log.d(TAG,this)
    }
    else Log.d(TAG,this.toString())
}

