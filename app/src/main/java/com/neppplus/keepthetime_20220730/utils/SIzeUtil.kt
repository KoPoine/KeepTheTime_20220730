package com.neppplus.keepthetime_20220730.utils

import android.content.Context
import android.util.TypedValue

class SizeUtil {

    companion object {
        fun dpToPx(context : Context, dp : Float) : Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
        }

        fun ConvertSPtoPX(context: Context, sp: Int): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                sp.toFloat(),
                context.resources.displayMetrics
            ).toInt()
        }
    }

}