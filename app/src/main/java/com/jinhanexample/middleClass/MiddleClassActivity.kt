package com.jinhanexample.middleClass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityMiddleClassBinding

class MiddleClassActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var b: ActivityMiddleClassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_middle_class)

        b.thread.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {

        when (p0!!.id) {
            R.id.thread -> {
                startActivity(Intent(this, ThreadActivity::class.java))
            }

        }
    }

}