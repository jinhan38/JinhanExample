package com.jinhanexample.mpchart

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jinhanexample.BaseActivity
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityMpchartListBinding

class MPChartList : BaseActivity(), View.OnClickListener {

    lateinit var b : ActivityMpchartListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_mpchart_list)
        setupListener()
    }

    override fun setupListener() {
        b.mpchartCubic.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.mpchart_cubic -> {
                startActivity(Intent(this, MPChartCubic::class.java))
            }
        }
    }
}