package com.jinhanexample.customChart

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import com.jinhanexample.customChart.fiveDayChart.FiveDayChartActivity
import android.content.Intent

class CustomChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_chart)

        findViewById<Button>(R.id.fiveDayChart).setOnClickListener {
            startActivity(Intent(this, FiveDayChartActivity::class.java))
        }
    }
}