//package com.jinhanexample.viewPager
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import androidx.databinding.DataBindingUtil
//import com.jinhanexample.BaseActivity
//import com.jinhanexample.R
//import com.jinhanexample.databinding.ActivityBaseViewPagerBinding
//import com.jinhanexample.viewPager.depth.DepthPageViewPager
//import com.jinhanexample.viewPager.flexibleHeight.FlexibleViewPagerHeightActivity
//import com.jinhanexample.viewPager.fragmentTrans.FragmentTransViewPager
//import com.jinhanexample.viewPager.survey.container.ViewPagerQuestionBaseActivity
//import com.jinhanexample.viewPager.touchTestViewPager.ui.TouchTestViewPagerActivity
//import com.jinhanexample.viewPager.viewPager2.ViewPager2Activity
//
//class BaseViewPagerActivity : BaseActivity(), View.OnClickListener {
//
//    lateinit var b: ActivityBaseViewPagerBinding
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        b = DataBindingUtil.setContentView(this, R.layout.activity_base_view_pager)
//        setupListener()
//    }
//
//    override fun setupListener() {
//        b.depthPageViewPager.setOnClickListener(this)
//        b.fragmentViewPager.setOnClickListener(this)
//        b.flexibleViewPager.setOnClickListener(this)
//        b.viewpager2.setOnClickListener(this)
//        b.btnSurveyViewPager.setOnClickListener(this)
//        b.touchListenerTestViewPager.setOnClickListener(this)
//    }
//
//    override fun onClick(p0: View?) {
//        when (p0!!.id) {
//            R.id.depth_page_view_pager -> {
//                startActivity(Intent(this, DepthPageViewPager::class.java))
//            }
//            R.id.fragment_view_pager -> {
//                startActivity(Intent(this, FragmentTransViewPager::class.java))
//            }
//            R.id.flexibleViewPager -> {
//                startActivity(Intent(this, FlexibleViewPagerHeightActivity::class.java))
//            }
//            R.id.viewpager2 -> {
//                startActivity(Intent(this, ViewPager2Activity::class.java))
//            }
//            R.id.btnSurveyViewPager -> {
//                startActivity(Intent(this, ViewPagerQuestionBaseActivity::class.java))
//            }
//            R.id.touchListenerTestViewPager -> {
//                startActivity(Intent(this, TouchTestViewPagerActivity::class.java))
//            }
//        }
//    }
//}