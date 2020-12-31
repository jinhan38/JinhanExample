package com.jinhanexample.viewPager.viewPager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2Adapter : FragmentStateAdapter {

    constructor(fragment: Fragment) : super(fragment)

    constructor(
        fragmentActivity: FragmentActivity,
        fragmentArrayList: ArrayList<Fragment>
    ) : super(fragmentActivity) {
        this.fragmentArrayList = fragmentArrayList
    }

    var fragmentArrayList = ArrayList<Fragment>()

    override fun getItemCount(): Int {
        return fragmentArrayList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentArrayList.get(position)
    }
}