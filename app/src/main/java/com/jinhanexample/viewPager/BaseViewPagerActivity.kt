package com.jinhanexample.viewPager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jinhanexample.BaseActivity
import com.jinhanexample.R
import com.jinhanexample.animation.ScaleAnimation
import com.jinhanexample.databinding.ActivityBaseViewPagerBinding
import com.jinhanexample.databinding.ActivityDepthPageViewPagerBinding

class BaseViewPagerActivity : BaseActivity(), View.OnClickListener {

    lateinit var b: ActivityBaseViewPagerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_base_view_pager)
        setupListener()
    }

    override fun setupListener() {
        b.depthPageViewPager.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.depth_page_view_pager -> {
                startActivity(Intent(this, DepthPageViewPager::class.java))
            }
        }
    }
}