package com.jinhanexample.Util

import android.content.Context
import android.view.View
import android.widget.FrameLayout

class LayoutParamsSize(

    context: Context,
    private var view: View,
    private var viewWidth: Int,
    private var viewHeight: Int

) : FrameLayout(context) {

    init {
        setParam()
    }

    private fun setParam() {
        val params: LayoutParams = view.layoutParams as LayoutParams
        params.width = viewWidth
        params.height = viewHeight
        view.layoutParams = params
    }


}