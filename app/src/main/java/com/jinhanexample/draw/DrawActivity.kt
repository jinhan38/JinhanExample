package com.jinhanexample.draw

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_draw.*

class DrawActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "DrawActivity"
    }

    var screenWidth: Int = 0
    var screenHeight: Int = 0
    lateinit var layoutWrap: FrameLayout
    lateinit var drawFrameLayout: FrameLayout
    lateinit var drawStar: ImageView

    var fmWidth: Int = 0
    var fmHeight: Int = 0
    var leftMarginWidth: Int = 0
    var topMarginHeight: Int = 0

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        layoutWrap = findViewById(R.id.layoutWrap)
        drawFrameLayout = findViewById(R.id.drawFrameLayout)
        drawStar = findViewById(R.id.drawStar)

        screenWidth = layoutWrap.measuredWidth
        screenHeight = layoutWrap.measuredHeight
        fmWidth = drawFrameLayout.measuredWidth
        fmHeight = drawFrameLayout.measuredHeight
        leftMarginWidth = (screenWidth - fmWidth) / 2
        topMarginHeight = (screenHeight - fmHeight) / 2

        Log.d(TAG, "onWindowFocusChanged: screenWidth : $screenWidth, screenHeight : $screenHeight")
        Log.d(TAG, "onWindowFocusChanged: fmWidth : $fmWidth, fmHeight : $fmHeight")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)



        drawButton.setOnClickListener {
            drawStar.visibility = View.VISIBLE
            var param = drawStar.layoutParams as FrameLayout.LayoutParams
            param.leftMargin = leftMarginWidth
            drawStar.layoutParams = param
        }

    }


}