package com.jinhanexample.scrollview

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jinhanexample.BaseActivity
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityScrollViewAnimationBinding
import com.jinhanexample.animation.progress.ValueAnimProgressBarActivity
import com.jinhanexample.scrollview.appBarHide.AppBarHideAnimationActivity

class BaseScrollViewAnimation : BaseActivity(), View.OnClickListener {

    lateinit var b: ActivityScrollViewAnimationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_scroll_view_animation)

        setupListener()
    }

    override fun setupListener() {
        b.collapsingToolbarLayout.setOnClickListener(this)
        b.collapsingToolbarLayout2.setOnClickListener(this)
        b.stickyScrollView.setOnClickListener(this)
        b.scrollShowAnimation.setOnClickListener(this)
        b.appBarHideAnimation.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.collapsing_toolbar_layout -> {
                startActivity(Intent(this, CollapsingToolbarLayout::class.java))
            }
            R.id.collapsing_toolbar_layout_2 -> {
                startActivity(Intent(this, CollapsingScrollView2Activity::class.java))
            }
            R.id.sticky_scroll_view -> {
                startActivity(Intent(this, StickyScrollView::class.java))
            }
            R.id.scroll_show_animation -> {
                startActivity(Intent(this, ScrollingShowAnimation::class.java))
            }
            R.id.appBarHideAnimation -> {
                startActivity(Intent(this, AppBarHideAnimationActivity::class.java))
            }
        }
    }
}