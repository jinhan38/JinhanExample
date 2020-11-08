package com.jinhanexample.floatingButton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jinhanexample.BaseActivity
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityBaseFloatingButtonBinding

class BaseFloatingButtonActivity : BaseActivity(), View.OnClickListener {

    lateinit var b: ActivityBaseFloatingButtonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_base_floating_button)
        setupListener()
    }

    override fun setupListener() {
        b.expandFloatingButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.expand_floating_button -> {
                startActivity(Intent(this, FloatingAnimationActivity::class.java))
            }
        }
    }
}