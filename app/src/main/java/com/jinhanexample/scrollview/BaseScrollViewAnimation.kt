package com.jinhanexample.scrollview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jinhanexample.BaseActivity
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityScrollViewAnimationBinding
import com.jinhanexample.mpchart.MPChartCubic

class BaseScrollViewAnimation : BaseActivity(), View.OnClickListener {

    lateinit var b: ActivityScrollViewAnimationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_scroll_view_animation)

        setupListener()
    }

    override fun setupListener() {
        b.collapsingToolbarLayout.setOnClickListener(this)
        b.stickyScrollView.setOnClickListener(this)
        b.scrollShowAnimation.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.collapsing_toolbar_layout -> {
                startActivity(Intent(this, CollapsingToolbarLayout::class.java))
            }
            R.id.sticky_scroll_view -> {
                startActivity(Intent(this, StickyScrollView::class.java))
            }
            R.id.scroll_show_animation -> {
                startActivity(Intent(this, ScrollingShowAnimation::class.java))
            }
        }
    }
}