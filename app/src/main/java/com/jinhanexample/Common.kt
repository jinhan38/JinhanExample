package com.jinhanexample

import android.content.Context
import android.util.TypedValue

class Common {

    companion object {
        public final fun getDP(context: Context, value: Int): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value.toFloat(),
                context.resources.displayMetrics
            )
        }
    }
}