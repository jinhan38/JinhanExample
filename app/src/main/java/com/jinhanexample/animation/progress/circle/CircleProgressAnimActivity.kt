package com.jinhanexample.animation.progress.circle

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityCircleProgressAnimBinding

class CircleProgressAnimActivity : AppCompatActivity() {

    lateinit var b: ActivityCircleProgressAnimBinding
    var circleWidth = 0f
    var circleHeight = 0f
    lateinit var progressWrap: FrameLayout
    lateinit var animation: Animation
    lateinit var circleProgressProgress: CircleProgress


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityCircleProgressAnimBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.button.setOnClickListener {
            circleProgressProgress.startAnimation(animation)
        }

    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        progressWrap = findViewById(R.id.progressWrap)
        circleWidth = progressWrap.width.toFloat()
        circleHeight = progressWrap.height.toFloat()
        Log.d("TAG", "onCreate: 사이즈 circleWidth : $circleWidth")
        Log.d("TAG", "onCreate: 사이즈 circleHeight : $circleHeight")

        circleProgressProgress = CircleProgress(
            this,
            circleWidth,
            circleHeight,
            R.color.brand_purple,
            R.color.gray
        )

        progressWrap.addView(circleProgressProgress)

        circleProgressProgress.startAngle = 120f
        circleProgressProgress.endAngle = 250f
        circleProgressProgress.baseStartAngle = 120f
        circleProgressProgress.baseEndAngle = 300f
        circleProgressProgress.color = R.color.brand_pink
        circleProgressProgress.baseColor = R.color.colorGray500
        circleProgressProgress.endPointColor = R.color.brand_green
        circleProgressProgress.postInvalidate()

        animation = CircleProgressAnimation(
            circleProgressProgress,
            0,
            250,
            1000
        )

    }



}