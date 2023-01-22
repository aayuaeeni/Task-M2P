package com.raju.m2p.presentation.utils

import android.content.res.Resources
import android.util.TypedValue

object AppUtility {
    fun convertDpToPx(marginInDp: Float): Int {
        val r: Resources = Resources.getSystem()
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            marginInDp,
            r.displayMetrics
        ).toInt()
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }
}