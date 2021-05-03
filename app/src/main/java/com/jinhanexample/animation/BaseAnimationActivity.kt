package com.jinhanexample.animation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jinhanexample.BaseActivity
import com.jinhanexample.R
import com.jinhanexample.animation.animBuilder.AnimBuilderClassActivity
import com.jinhanexample.animation.customLoadingView.CustomLoadingViewActivity
import com.jinhanexample.animation.progress.ValueAnimProgressBarActivity
import com.jinhanexample.animation.progress.circle.CircleProgressAnimActivity
import com.jinhanexample.animation.splashAnim.SplashAnimFirstActivity
import com.jinhanexample.databinding.ActivityBaseBinding

class BaseAnimationActivity : BaseActivity(), View.OnClickListener {
    lateinit var b: ActivityBaseBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_base)
        setupListener()
    }

    override fun setupListener() {
        b.scaleAnimation.setOnClickListener(this)
        b.imageSlide.setOnClickListener(this)
        b.LockedBottomSheet.setOnClickListener(this)
        b.animationBuilder.setOnClickListener(this)
        b.progressBarAnim.setOnClickListener(this)
        b.circleProgress.setOnClickListener(this)
        b.customLoadingView.setOnClickListener(this)
        b.splashAnim.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.scale_animation -> {
                startActivity(Intent(this, ScaleAnimation::class.java))
            }
            R.id.imageSlide -> {
                startActivity(Intent(this, ImageSlideActivity::class.java))
            }
            R.id.LockedBottomSheet -> {
                startActivity(Intent(this, LockedBottomSheetActivity::class.java))
            }
            R.id.animationBuilder -> {
                startActivity(Intent(this, AnimBuilderClassActivity::class.java))
            }
            R.id.progressBarAnim -> {
                startActivity(Intent(this, ValueAnimProgressBarActivity::class.java))
            }
            R.id.circleProgress -> {
                startActivity(Intent(this, CircleProgressAnimActivity::class.java))
            }
            R.id.customLoadingView -> {
                startActivity(Intent(this, CustomLoadingViewActivity::class.java))
            }
            R.id.splashAnim -> {
                startActivity(Intent(this, SplashAnimFirstActivity::class.java))
            }
        }
    }
}