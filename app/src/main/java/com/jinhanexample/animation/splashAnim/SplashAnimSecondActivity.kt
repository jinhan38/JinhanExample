package com.jinhanexample.animation.splashAnim

import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivitySplashAnimSecondBinding

class SplashAnimSecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashAnimSecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_anim_second)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)



        binding.btnSplashSecond.apply {

//            animate().alpha(0.1f).scaleX(0.1f).scaleY(0.1f)
//                .setDuration(1000)
//                .setInterpolator(AccelerateInterpolator())

        }

    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

}