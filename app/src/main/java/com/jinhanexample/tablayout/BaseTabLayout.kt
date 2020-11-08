package com.jinhanexample.tablayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jinhanexample.BaseActivity
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityBaseTabLayoutBinding

class BaseTabLayout : BaseActivity(), View.OnClickListener {

    lateinit var b: ActivityBaseTabLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_base_tab_layout)

        setupListener()
    }

    override fun setupListener() {
        b.circleIndicator.setOnClickListener(this)
        b.rectanglesIndicator.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {

        when (p0!!.id) {
            R.id.circle_indicator -> {
                startActivity(Intent(this, CircleTabIndicator::class.java))
            }
            R.id.rectangles_indicator -> {
                startActivity(Intent(this, RectangleTabIndicator::class.java))
            }
        }
    }
}