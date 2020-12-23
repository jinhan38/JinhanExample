package com.jinhanexample.customChart.fiveDayChart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_five_day_chart.*

class FiveDayChartActivity : AppCompatActivity() {

    private var viewWidth = 0f
    private var viewHeight = 0f
    lateinit var fiveDayChart: FiveDayChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five_day_chart)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        viewWidth = fiveDayChartView.width.toFloat()
        viewHeight = fiveDayChartView.height.toFloat()
        fiveDayChart = FiveDayChart(this, viewWidth, viewHeight, R.color.colorGray400)

        fiveDayChartView.addView(fiveDayChart)

        fiveDayChart.postInvalidate()

        
    }
}