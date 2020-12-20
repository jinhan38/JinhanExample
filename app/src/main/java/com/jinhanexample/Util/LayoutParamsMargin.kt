package com.jinhanexample.Util

import android.content.Context
import android.view.View
import android.widget.FrameLayout

class LayoutParamsMargin(

    context: Context,
    private var view: View,
    private var leftMargin: Int,
    private var topMargin: Int,
    private var rightMargin: Int,
    private var bottomMargin: Int

) : FrameLayout(context) {

    init {
        setParam()
    }

    private fun setParam() {
        val params: LayoutParams = view.layoutParams as LayoutParams
        params.setMargins(leftMargin, topMargin, rightMargin, bottomMargin)
        view.layoutParams = params
    }


}