package com.jinhanexample.customChart.varChart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_bar_chart_view.*

class BarChartViewActivity : AppCompatActivity() {

    lateinit var barChartView: BarChartView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart_view)


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        var scoreArray: ArrayList<Int> = ArrayList()
        scoreArray.add(15)
        scoreArray.add(12)
        scoreArray.add(3)
        scoreArray.add(6)

        barChartView = BarChartView(this, chart.width.toFloat(), chart.height.toFloat(), scoreArray)

        chart.addView(barChartView)
    }
}