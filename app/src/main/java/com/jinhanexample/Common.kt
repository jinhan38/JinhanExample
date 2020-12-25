package com.jinhanexample

import android.animation.ObjectAnimator
import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.animation.Interpolator

class Common {

    companion object {

         fun getDP(context: Context, value: Int): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value.toFloat(),
                context.resources.displayMetrics
            )
        }

        fun getSP(context: Context, value: Int): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                value.toFloat(),
                context.resources.displayMetrics
            )
        }

        fun makeAnimation(
            v: View?,
            type: String?,
            value: Float,
            duration: Int,
            interpolator: Interpolator
        ): ObjectAnimator? {
            val anim = ObjectAnimator.ofFloat(v, type, value)
            anim.duration = duration.toLong()
            anim.interpolator = interpolator
            return anim
        }


        fun setColor(context: Context, color: Int): Int {
            return context.resources.getColor(color, null)
        }
    }

}