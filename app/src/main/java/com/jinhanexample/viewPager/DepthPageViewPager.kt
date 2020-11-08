package com.jinhanexample.viewPager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityDepthPageViewPagerBinding
import com.jinhanexample.viewPager.fragment.FirstFragment
import com.jinhanexample.viewPager.fragment.SecondFragment
import com.jinhanexample.viewPager.fragment.ThirdFragment


class DepthPageViewPager : AppCompatActivity() {

    lateinit var b: ActivityDepthPageViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_depth_page_view_pager)


        // The pager adapter, which provides the pages to the view pager widget.
//        val fragmentList = ArrayList<Fragment>()
//        fragmentList.add(FirstFragment())
//        fragmentList.add(SecondFragment())
//        fragmentList.add(ThirdFragment())

        val viewPagerAdapter = ScreenSlidePagerAdapter(this)
        b.pager.adapter = viewPagerAdapter
        b.pager.setPageTransformer(DepthPageTransformer())
    }

    override fun onBackPressed() {
        if (b.pager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            b.pager.currentItem = b.pager.currentItem - 1
        }
    }

}

/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */
private class ScreenSlidePagerAdapter(fa: FragmentActivity) :
    FragmentStateAdapter(
        fa
    ) {


    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            else -> ThirdFragment()
        }

    }
}
