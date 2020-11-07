package com.jinhanexample.mpchart

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.jinhanexample.R

class MPChartCubic : AppCompatActivity(), View.OnClickListener  {


    companion object {
        private const val TAG = "MPChartCubic"
    }

    lateinit var lineChart: LineChart
    var values = ArrayList<Entry>()
    lateinit var set1: LineDataSet


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_p_chart_cubic)



        values.add(Entry(0f, 50f))
        values.add(Entry(2f, 80f))
        values.add(Entry(3f, 60f))
        values.add(Entry(4f, 40f))
        values.add(Entry(5f, 50f))
        values.add(Entry(6f, 90f))
        values.add(Entry(7f, 20f))
        values.add(Entry(8f, 70f))
        values.add(Entry(9f, 50f))

        lineChart = findViewById(R.id.chart_first)

        lineChart.setViewPortOffsets(0f, 0f, 0f, 0f)

        //선 위쪽 배경 컬러
        lineChart.setBackgroundColor(Color.TRANSPARENT)
//        lineChart.setBackgroundColor(Color.rgb(100, 250, 180))
        lineChart.description.isEnabled = false

        lineChart.setTouchEnabled(false)
        lineChart.isDragEnabled = false
        lineChart.setScaleEnabled(false)

        lineChart.setPinchZoom(false)
        lineChart.setDrawGridBackground(false)
        lineChart.maxHighlightDistance = 500f

        var x: XAxis = lineChart.xAxis
        x.isEnabled = false

        var y: YAxis = lineChart.axisLeft

        //폰트
//        tfLight = Typeface.createFromAsset(assets, "OpenSans-Light.ttf")
//        y.typeface = tfLight
        y.setLabelCount(25, false)

        //y축 가장 왼쪽 수치값 컬러
        y.textColor = Color.TRANSPARENT

        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)

        //데이터 수치의 가로줄
        y.setDrawGridLines(false)
//        y.gridColor = Color.WHITE

        //y축 가장 왼쪽의 세로줄
//        y.axisLineColor = Color.BLUE
//        y.axisLineWidth = 10f

        lineChart.axisRight.isEnabled = false

        lineChart.legend.isEnabled = false
        lineChart.animateXY(1000, 1000)
//        lineChart_second.animateXY(1000, 1000)
        lineChart.invalidate()



        setData(lineChart, 7, 100f, this.values, false)

        setupListener()
        
    }
    
    @SuppressLint("UseCompatLoadingForDrawables")
    fun setData(
        lineChart: LineChart,
        count: Int,
        range: Float,
        values: ArrayList<Entry>,
        circle: Boolean
    ) {


//        for (i in 0..count) {
//            val v: Float = (((Math.random() * (range + 1)) + 20).toFloat())
//            values.add(Entry(i.toFloat(), v))
//        }


        Log.d(TAG, "setData: 진입 $values")



        if (lineChart.data != null && lineChart.data.dataSetCount > 0) {
            set1 = lineChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            Log.d(TAG, "setData: notnull 진입")


            lineChart.data.notifyDataChanged()
            lineChart.notifyDataSetChanged()
        } else {

            Log.d(TAG, "setData: else 진입")
            set1 = LineDataSet(values, "DataSet 1")

            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.cubicIntensity = 0.2f
            set1.setDrawFilled(true)

            //데이터 circle 찍을지 말지
            set1.setDrawCircles(false)


            //data 안쪽 원
            set1.circleHoleColor = Color.GREEN
            set1.circleHoleRadius = 5f

            //바깥쪽 원
            set1.circleRadius = 15f
            set1.setCircleColor(Color.RED)

            //선 두께
            set1.lineWidth = 5f
            set1.highLightColor = Color.BLACK

            //선 컬러
            set1.color = Color.BLACK

            //선 아래 배경 컬러
            set1.fillColor = Color.WHITE

            //선 아래 배경 투명도
            set1.fillAlpha = 255
            set1.setDrawHorizontalHighlightIndicator(false)
            set1.fillFormatter =
                IFillFormatter { dataSet, dataProvider -> lineChart.axisLeft.axisMinimum }


            set1.setGradientColor(Color.YELLOW, Color.GREEN)

            Log.d(TAG, "setData: ${set1.gradientColor}")

            var data = LineData(set1)

//            data.setValueTypeface(tfLight)
            data.setValueTextSize(13f)
            data.setDrawValues(true)



            Log.d(TAG, "setData: $data")
            lineChart.data = data

            //데이터 수치 표시
//            lineChart.data.setDrawValues(true)
        }

    }


    fun setupListener() {
        (findViewById<TextView>(R.id.monday)).setOnClickListener(this)
        (findViewById<TextView>(R.id.tuesday)).setOnClickListener(this)
        (findViewById<TextView>(R.id.wednesday)).setOnClickListener(this)
        (findViewById<TextView>(R.id.thursday)).setOnClickListener(this)
        (findViewById<TextView>(R.id.friday)).setOnClickListener(this)
        (findViewById<TextView>(R.id.saturday)).setOnClickListener(this)
        (findViewById<TextView>(R.id.sunday)).setOnClickListener(this)
    }
    override fun onClick(p0: View?) {

        when (p0!!.id) {
            R.id.monday -> {

            }
            R.id.tuesday -> {
                Log.d(TAG, "onClick: ${lineChart.data.dataSets[0].getEntryForIndex(2)}")
                var sets: List<ILineDataSet> = lineChart.data.dataSets

                var circleSet: Entry? = lineChart.data.getDataSetByIndex(0).getEntryForIndex(2)
                Log.d(TAG, "onClick: circleSet : $circleSet")
                var circleSetArray = ArrayList<Entry>()
                circleSetArray.add(circleSet!!)
                Log.d(TAG, "onClick: circleSetArray : $circleSetArray")
                set1 = LineDataSet(circleSetArray, "DataSet 1")
                Log.d(TAG, "onClick: set : $set1")
                Log.d(TAG, "onClick: set1 : $set1")

//                    var set = iSet as LineDataSet
                if (set1.isDrawCirclesEnabled) {
                    set1.setDrawCircles(false)
                    set1.setDrawCircleHole(false)
                } else {
                    set1.setDrawCircles(true)
                    set1.setDrawCircleHole(true)
                }

                lineChart.invalidate();
            }
            R.id.wednesday -> {
                var sets: List<ILineDataSet> = lineChart.data.dataSets

                for (iSet in sets) {
                    val set = iSet as LineDataSet
                    if (set.isDrawCirclesEnabled) set.setDrawCircles(false) else set.setDrawCircles(
                        true
                    )
                }
                lineChart.invalidate()
            }
            R.id.thursday -> {

            }
            R.id.friday -> {

            }
            R.id.saturday -> {

            }
            R.id.sunday -> {

            }
        }

    }
}