package com.jinhanexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jinhanexample.animation.BaseAnimationActivity
import com.jinhanexample.codingLecture.CodingLectureMainActivity
import com.jinhanexample.databinding.ActivityMainBinding
import com.jinhanexample.floatingButton.BaseFloatingButtonActivity
import com.jinhanexample.fragment.BaseFragmentActivity
import com.jinhanexample.fragment.ColorFragmentActivity
import com.jinhanexample.jetBrain.JetBrainBaseActivity
import com.jinhanexample.mpchart.MPChartList
import com.jinhanexample.scrollview.BaseScrollViewAnimation
import com.jinhanexample.stopWatch.StopWatchActivity
import com.jinhanexample.tablayout.BaseTabLayout
import com.jinhanexample.textView.TextViewListActivity
import com.jinhanexample.viewPager.BaseViewPagerActivity

class MainActivity : BaseActivity(), View.OnClickListener {


    lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupListener()

    }


    override fun setupListener() {
        b.mpchart.setOnClickListener(this)
        b.scrollAnimation.setOnClickListener(this)
        b.tabLayout.setOnClickListener(this)
        b.animation.setOnClickListener(this)
        b.viewPager.setOnClickListener(this)
        b.floatingButton.setOnClickListener(this)
        b.textView.setOnClickListener(this)
        b.jetBrain.setOnClickListener(this)
        b.codingLecture.setOnClickListener(this)
        b.stopWatch.setOnClickListener(this)
        b.fragment.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {

            R.id.mpchart -> {
                startActivity(Intent(this, MPChartList::class.java))
            }
            R.id.scroll_animation -> {
                startActivity(Intent(this, BaseScrollViewAnimation::class.java))
            }

            R.id.tab_layout -> {
                startActivity(Intent(this, BaseTabLayout::class.java))
            }

            R.id.animation -> {
                startActivity(Intent(this, BaseAnimationActivity::class.java))
            }

            R.id.viewPager -> {
                startActivity(Intent(this, BaseViewPagerActivity::class.java))
            }
            R.id.floatingButton -> {
                startActivity(Intent(this, BaseFloatingButtonActivity::class.java))
            }
            R.id.textView -> {
                startActivity(Intent(this, TextViewListActivity::class.java))
            }
            R.id.jetBrain -> {
                startActivity(Intent(this, JetBrainBaseActivity::class.java))
            }
            R.id.codingLecture -> {
                startActivity(Intent(this, CodingLectureMainActivity::class.java))
            }
            R.id.stopWatch -> {
                startActivity(Intent(this, StopWatchActivity::class.java))
            }
            R.id.fragment -> {
                startActivity(Intent(this, BaseFragmentActivity::class.java))
            }

        }
    }
}