package com.jinhanexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jinhanexample.animation.BaseAnimationActivity
import com.jinhanexample.calendar.CustomCalendarActivity
import com.jinhanexample.codingLecture.CodingLectureMainActivity
import com.jinhanexample.databinding.ActivityMainBinding
import com.jinhanexample.designPattern.DesignPatternActivity
import com.jinhanexample.draw.DrawActivity
import com.jinhanexample.draw.layoutParams.DrawLayoutParamsActivity
import com.jinhanexample.floatingButton.BaseFloatingButtonActivity
import com.jinhanexample.fragment.BaseFragmentActivity
import com.jinhanexample.jetBrain.JetBrainBaseActivity
import com.jinhanexample.middleClass.MiddleClassActivity
import com.jinhanexample.mpchart.MPChartList
import com.jinhanexample.mvvmSample.java.author.ui.AuthorActivityByJava
import com.jinhanexample.mvvmSample.kotlin.author.ui.AuthorActivityByKotlin
import com.jinhanexample.mvvmTodo.ui.MVVMTodoActivity
import com.jinhanexample.recyclerView.RecyclerViewActivity
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
        b.middleClass.setOnClickListener(this)
        b.calendarView.setOnClickListener(this)
        b.recyclerView.setOnClickListener(this)
        b.mvvmTodo.setOnClickListener(this)
        b.draw.setOnClickListener(this)
        b.designPattern.setOnClickListener(this)
        b.mvvmSampleKotlin.setOnClickListener(this)
        b.mvvmSampleJava.setOnClickListener(this)
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
            R.id.middleClass -> {
                startActivity(Intent(this, MiddleClassActivity::class.java))
            }
            R.id.calendarView -> {
                startActivity(Intent(this, CustomCalendarActivity::class.java))
            }
            R.id.recyclerView -> {
                startActivity(Intent(this, RecyclerViewActivity::class.java))
            }
            R.id.mvvmTodo -> {
                startActivity(Intent(this, MVVMTodoActivity::class.java))
            }
            R.id.draw -> {
                startActivity(Intent(this, DrawActivity::class.java))
            }
            R.id.designPattern -> {
                startActivity(Intent(this, DesignPatternActivity::class.java))
            }
            R.id.mvvmSampleKotlin -> {
                startActivity(Intent(this, AuthorActivityByKotlin::class.java))
            }
            R.id.mvvmSampleJava -> {
                startActivity(Intent(this, AuthorActivityByJava::class.java))
            }

        }
    }
}