package com.jinhanexample.designPattern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.R
import com.jinhanexample.designPattern.factory.FactoryPatternActivity
import kotlinx.android.synthetic.main.activity_design_pattern.*

class DesignPatternActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_design_pattern)


        factoryPattern.setOnClickListener {
            startActivity(Intent(this, FactoryPatternActivity::class.java))
        }
    }
}