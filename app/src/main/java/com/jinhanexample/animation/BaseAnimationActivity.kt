package com.jinhanexample.animation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jinhanexample.BaseActivity
import com.jinhanexample.R
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
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.scale_animation -> {
                startActivity(Intent(this, ScaleAnimation::class.java))
            }
        }
    }
}