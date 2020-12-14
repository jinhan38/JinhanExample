package com.jinhanexample.draw

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import com.jinhanexample.animation.animBuilder.TranslateAnimationBuilder
import com.jinhanexample.others.Extentions.setLayoutParamsMargin
import kotlinx.android.synthetic.main.activity_draw.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class DrawActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "DrawActivity"
    }

    var screenWidth: Int = 0
    var screenHeight: Int = 0
    lateinit var layoutWrap: FrameLayout
    lateinit var parentFrameLayout: FrameLayout
    lateinit var drawStar: ImageView

    var rotateNum = 0

    var fmWidth: Int = 0
    var fmHeight: Int = 0
    var leftMargin: Int = 0
    var topMargin: Int = 0
    var defaultLeftMargin: Int = 0
    var defaultTopMargin: Int = 0
    var selfLeftMargin = 0

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        layoutWrap = findViewById(R.id.layoutWrap)
        parentFrameLayout = findViewById(R.id.parentFrameLayout)
        drawStar = findViewById(R.id.drawStar)

        screenWidth = layoutWrap.measuredWidth
        screenHeight = layoutWrap.measuredHeight
        fmWidth = parentFrameLayout.measuredWidth
        fmHeight = parentFrameLayout.measuredHeight
        defaultLeftMargin = screenWidth - fmWidth
        defaultTopMargin = screenHeight - fmHeight
        selfLeftMargin = drawStar.measuredWidth / 2


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)

        drawButton.setOnClickListener {

            when (rotateNum) {

                0 -> {
                    rotateNum += 1
                    leftMargin = (defaultLeftMargin / 2) - selfLeftMargin
                    topMargin = (defaultTopMargin / 2) - selfLeftMargin
                }
                1 -> {
                    rotateNum += 1
                    leftMargin = (screenWidth / 2) - selfLeftMargin
                    topMargin = (defaultTopMargin / 2) - selfLeftMargin
                }
                2 -> {
                    rotateNum += 1
                    leftMargin = ((screenWidth / 2) + (fmWidth / 2)) - selfLeftMargin
                    topMargin = (defaultTopMargin / 2) - selfLeftMargin
                }
                3 -> {
                    rotateNum += 1
                    leftMargin = (defaultLeftMargin / 2) - selfLeftMargin
                    topMargin = (screenHeight / 2) - selfLeftMargin
                }
                4 -> {
                    rotateNum += 1
                    leftMargin = (screenWidth / 2) - selfLeftMargin
                    topMargin = (screenHeight / 2) - selfLeftMargin
                }
                5 -> {
                    rotateNum += 1
                    leftMargin = ((screenWidth / 2) + (fmWidth / 2)) - selfLeftMargin
                    topMargin = (screenHeight / 2) - selfLeftMargin
                }
                6 -> {
                    rotateNum += 1
                    leftMargin = (defaultLeftMargin / 2) - selfLeftMargin
                    topMargin = ((screenHeight / 2) + (fmHeight / 2)) - selfLeftMargin
                }
                7 -> {
                    rotateNum += 1
                    leftMargin = (screenWidth / 2) - selfLeftMargin
                    topMargin = ((screenHeight / 2) + (fmHeight / 2)) - selfLeftMargin
                }
                8 -> {
                    rotateNum = 0
                    leftMargin = ((screenWidth / 2) + (fmWidth / 2)) - selfLeftMargin
                    topMargin = ((screenHeight / 2) + (fmHeight / 2)) - selfLeftMargin
                }

            }

//            setAnimation(drawStar, leftMargin.toFloat(), topMargin.toFloat())
            drawStar.setLayoutParamsMargin(leftMargin, topMargin)

        }

    }

    private fun setAnimation(view: View, toX: Float, toY: Float) {

        CoroutineScope(Main).launch {
//        val animationType = Animation.RELATIVE_TO_SELF // 본인 뷰를 기준으로하여 퍼센트 값으로 입력
//            int animationType = Animation.RELATIVE_TO_PARENT;// 부모뷰 뷰를 기준으로하여 퍼센트 값으로 입력
            val animationType = Animation.ABSOLUTE;// 절대값으로 입력

            TranslateAnimationBuilder.Builder(
                view,
                Animation.RELATIVE_TO_SELF, 0f,
                animationType, toX,
                Animation.RELATIVE_TO_SELF, 0f,
                animationType, toY,
                1000
            )
                .setFillAfter(true)
                .setFillEnabled(true)
                .setInterpolator(AccelerateInterpolator())
                .setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        Log.d(TAG, "onAnimationEnd: 애니메이션 끝남")

                        drawStar.setLayoutParamsMargin(toX.toInt(), toY.toInt())
                    }

                    override fun onAnimationRepeat(p0: Animation?) {
                    }

                })
                .build()
        }
    }


}