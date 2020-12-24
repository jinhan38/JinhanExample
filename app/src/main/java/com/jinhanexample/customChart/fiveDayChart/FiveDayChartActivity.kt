package com.jinhanexample.customChart.fiveDayChart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_five_day_chart.*

class FiveDayChartActivity : AppCompatActivity() {

    private var viewLeft = 0f
    private var viewTop = 0f
    private var viewRight = 0f
    private var viewBottom = 0f
    lateinit var fiveDayChart: FiveDayChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five_day_chart)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        viewLeft = fiveDayChartView.left.toFloat()
        viewTop = fiveDayChartView.top.toFloat()
        viewRight = fiveDayChartView.right.toFloat()
        viewBottom = fiveDayChartView.bottom.toFloat()
        var scoreArray: ArrayList<Int> = ArrayList()
        scoreArray.add(33)
        scoreArray.add(50)
        scoreArray.add(77)
        scoreArray.add(66)
        scoreArray.add(100)

        fiveDayChart =
            FiveDayChart(
                this,
                fiveDayChartView.width.toFloat(),
                fiveDayChartView.height.toFloat(),
                R.color.white,
                R.color.brand_gray,
                scoreArray
            )

        fiveDayChartView.addView(fiveDayChart)

        fiveDayChart.postInvalidate()


    }
}