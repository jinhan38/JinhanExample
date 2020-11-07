package com.jinhanexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jinhanexample.databinding.ActivityMainBinding
import com.jinhanexample.mpchart.MPChartList

class MainActivity : BaseActivity(), View.OnClickListener {


    lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupListener()

    }


    override fun setupListener() {
        b.mpchart.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {

            R.id.mpchart -> {
                startActivity(Intent(this, MPChartList::class.java))
            }

        }
    }
}