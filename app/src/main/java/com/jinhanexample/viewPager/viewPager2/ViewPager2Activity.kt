package com.jinhanexample.viewPager.viewPager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.jinhanexample.R
import com.jinhanexample.viewPager.flexibleHeight.FlexibleFragmentFirst
import com.jinhanexample.viewPager.flexibleHeight.FlexibleFragmentSecond
import com.jinhanexample.viewPager.flexibleHeight.FlexibleFragmentThird
import kotlinx.android.synthetic.main.activity_view_pager2.*

class ViewPager2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)

        var fragmentArrayList = ArrayList<Fragment>()
        fragmentArrayList.add(FlexibleFragmentFirst())
        fragmentArrayList.add(FlexibleFragmentSecond())
        fragmentArrayList.add(FlexibleFragmentThird())
        val adapter = ViewPager2Adapter(this, fragmentArrayList)

        viewPager.adapter = adapter
        Log.d("TAG", "onCreate: count : " + viewPager.childCount)

    }

}