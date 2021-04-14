package com.jinhanexample.scrollview.StickyScrollViewSecond

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityStickyScrollViewSecondBinding
import com.jinhanexample.scrollview.appBarHide.AppBarHideAnimationFragment

class StickyScrollViewSecondActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "StickyScrollViewSecondA"
    }

    lateinit var b: ActivityStickyScrollViewSecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_sticky_scroll_view_second)



        val fragment: Fragment = StickyScrollViewSecondFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.stickyContainer, fragment)
        fragmentTransaction.commit()

    }

}