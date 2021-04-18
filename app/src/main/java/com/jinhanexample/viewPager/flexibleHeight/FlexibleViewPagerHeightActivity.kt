package com.jinhanexample.viewPager.flexibleHeight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_flexible_view_pager_height.*

class FlexibleViewPagerHeightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexible_view_pager_height)

        val first = FlexibleFragmentFirst()
        val second = FlexibleFragmentSecond()
        val third = FlexibleFragmentThird()


        //offscreenPageLimit 속성을 주지 않으면 스크롤 했을 때
        //viewpager의 높이가 각 fragment들의 높이로 변합니다.
        flexibleViewPager.offscreenPageLimit = 0

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addItem(first)
        viewPagerAdapter.addItem(second)
        viewPagerAdapter.addItem(third)
        flexibleViewPager.adapter = viewPagerAdapter

        flexibleViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {

            }

            override fun onPageSelected(position: Int) {
                Log.d(TAG, "onPageSelected height : ${flexibleViewPager.getChildAt(position).height}")
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    companion object {
        private const val TAG = "FlexibleViewPagerHeight"
    }
}