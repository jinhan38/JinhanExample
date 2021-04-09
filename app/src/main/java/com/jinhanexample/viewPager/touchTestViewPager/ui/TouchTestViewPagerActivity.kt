//package com.jinhanexample.viewPager.touchTestViewPager.ui
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.Fragment
//import com.jinhanexample.databinding.TouchTestViewPagerActivityBinding
//import com.jinhanexample.viewPager.touchTestViewPager.adapter.ViewPagerAdapter
//
//class TouchTestViewPagerActivity : AppCompatActivity() {
//
//    lateinit var b: TouchTestViewPagerActivityBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        b = TouchTestViewPagerActivityBinding.inflate(layoutInflater)
//        setContentView(b.root)
//
//        val fragmentArray = ArrayList<Fragment>()
//        for (i in 0 until 4) {
//            fragmentArray.add(TouchTestViewPagerFragment())
//        }
//
//        val viewPagerAdapter =
//            ViewPagerAdapter(
//                supportFragmentManager,
//                fragmentArray)
//
//        b.viewPager.isSwipeEnable = false
//        b.viewPager.adapter = viewPagerAdapter
//
//
//    }
//}