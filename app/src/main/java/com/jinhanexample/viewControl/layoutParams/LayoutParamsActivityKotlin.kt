package com.jinhanexample.viewControl.layoutParams

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_layout_params.*


class LayoutParamsActivityKotlin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_params)

        button.setOnClickListener {
            changeTextViewSize()
        }
    }

    private fun changeTextViewSize() {

        //It get layoutParams of view. you can not change margin of view.
        // If you want to change width or height of view, It is good
        var params = tvFirst.layoutParams
        params.width = getDP(this, 250).toInt()
        tvFirst.layoutParams = params


        val marginParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(tvSecond.layoutParams)
        marginParams.setMargins(30, 300, 0, 0)
        tvSecond.layoutParams = marginParams


        //If you don't set margin value and set only width and height,
        //view is sticky on top and left
        val thirdParams = FrameLayout.LayoutParams(
            getDP(this, 250).toInt(),
            getDP(this, 100).toInt()
        )
        thirdParams.setMargins(50, 50, 0, 0)

        tvThird.layoutParams = thirdParams

    }

    fun getDP(context: Context, value: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            context.resources.displayMetrics
        )
    }
}