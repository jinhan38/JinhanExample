package com.jinhanexample.customChart.fiveDayChart.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter : FragmentPagerAdapter {



    constructor(fm: FragmentManager) : super(fm)
    constructor(fm: FragmentManager, behavior: Int) : super(fm, behavior)

    private var fragmentArrayList = ArrayList<Fragment>()

    override fun getCount(): Int {
        return fragmentArrayList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentArrayList[position]
    }

    fun addItem(fragment: Fragment) {
        this.fragmentArrayList.add(fragment)
    }

    fun removeItem(fragment: Fragment) {
        this.fragmentArrayList[fragmentArrayList.size - 1]
    }
}