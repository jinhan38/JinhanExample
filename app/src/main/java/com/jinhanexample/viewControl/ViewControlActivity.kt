package com.jinhanexample.viewControl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.R
import com.jinhanexample.viewControl.layoutParams.LayoutParamsActivityJava
import com.jinhanexample.viewControl.layoutParams.LayoutParamsActivityKotlin
import kotlinx.android.synthetic.main.activity_view_control.*

class ViewControlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_control)

        layoutParamsKotlin.setOnClickListener {
            startActivity(Intent(this, LayoutParamsActivityKotlin::class.java))
        }

        layoutParamsJava.setOnClickListener {
            startActivity(Intent(this, LayoutParamsActivityJava::class.java))
        }
    }
}