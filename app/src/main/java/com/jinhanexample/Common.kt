package com.jinhanexample

import android.R
import android.animation.ObjectAnimator
import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.animation.Interpolator
import android.widget.TextView
import android.widget.Toast

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
            interpolator: Interpolator,
        ): ObjectAnimator? {
            val anim = ObjectAnimator.ofFloat(v, type, value)
            anim.duration = duration.toLong()
            anim.interpolator = interpolator
            return anim
        }


        fun setColor(context: Context, color: Int): Int {
            return context.resources.getColor(color, null)
        }

        /**
         * 토스트 layout_gravity center 정렬
         *
         * @param context
         * @param msg
         */
        fun setToastCenter(context: Context?, msg: String?) {
            val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            val v = toast.view!!.findViewById<View>(R.id.message) as TextView
            if (v != null) v.gravity = Gravity.CENTER
            toast.show()
        }


        /** * 토스트 텍스트 gravity center
         */
        fun setToastGravityCenter(context: Context?, msg: String?) {
            val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            val v = toast.view!!.findViewById<View>(R.id.message) as TextView
            if (v != null) v.gravity = Gravity.CENTER
            toast.show()

//        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.BOTTOM, Gravity.CENTER_HORIZONTAL, 0);
//        toast.show();
        }

    }

}