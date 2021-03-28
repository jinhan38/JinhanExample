package com.jinhanexample.viewPager.touchTestViewPager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jinhanexample.viewPager.touchTestViewPager.ui.TouchTestViewPagerFragment

class ViewPager2Adapter : FragmentStateAdapter {

    var fragmentArray = ArrayList<TouchTestViewPagerFragment>()

    constructor(
        fragmentActivity: FragmentActivity,
        fragmentArray: ArrayList<TouchTestViewPagerFragment>,
    ) : super(fragmentActivity){
        this.fragmentArray = fragmentArray
    }


    override fun getItemCount(): Int {
        return fragmentArray.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentArray[position]
    }



}