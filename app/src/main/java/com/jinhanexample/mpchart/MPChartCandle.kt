package com.jinhanexample.mpchart

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.interfaces.datasets.IDataSet
import com.github.mikephil.charting.utils.MPPointD
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityMPChartCandleBinding

@Suppress("DEPRECATION")
class MPChartCandle : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    companion object {
        private const val TAG = "MPChartCandle"
    }

    lateinit var b: ActivityMPChartCandleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_m_p_chart_candle)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        title = "MPChartCandle"
        chartSetting()

        setupListener()

    }

    private fun chartSetting() {
        b.seekBar1.setOnSeekBarChangeListener(this)
        b.seekBar1.progress = 40
        b.seekBar2.setOnSeekBarChangeListener(this)
        b.seekBar2.progress = 100


        //최대로 보일 수 있는 수치, 수정 안하는 것이 좋음
        b.chart1.setMaxVisibleValueCount(60)

        //zoom 기능, true로 하면 상하좌우 동시에 zoom
        //false는 상하, 좌우 따로 줌 가능
        b.chart1.setPinchZoom(true)

        b.chart1.setBackgroundColor(Color.WHITE) // 차트 바깥쪽 컬러
        b.chart1.setGridBackgroundColor(resources.getColor(R.color.design_default_color_primary)) //차트 안쪽 컬러

        //x축 라벨 설정
        val xAxis: XAxis = b.chart1.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM // BOTTOM이면 라벨이 Bottom에 위치
        xAxis.setDrawGridLines(false)  //true값을 주면 x축의 수치마다 구분선을 넣어준다.
        xAxis.setLabelCount(7, false)    //X축에서 보여줄 라벨의 숫자. 최대 25, 최소 2, 디폴트 6
        //TODO:: 확대를 많이하면  Value값이 소수점도 나타낸다. 소수점 없애는 것 필요
        //차트의 뒷줄 길이 수정하는 것 필요
        //차트가 이동할 때 마다 오른쪽에 해당 차트의 상단에 마커 찍기


        val leftAxis: YAxis = b.chart1.axisLeft
        leftAxis.isEnabled = false
//        leftAxis.setLabelCount(15, false)  //Y축에서 보여줄 라벨의 숫자. 최대 25, 최소 2, 디폴트 6
//        leftAxis.setDrawGridLines(false)
//        leftAxis.setDrawAxisLine(false)

        val rightAxis: YAxis = b.chart1.axisRight
        rightAxis.isEnabled = true
        rightAxis.setLabelCount(15, false)  //Y축에서 보여줄 라벨의 숫자. 최대 25, 최소 2, 디폴트 6
        rightAxis.setDrawGridLines(false)
        rightAxis.setDrawAxisLine(false)


        //차트 오른쪽 하단부에 설명 추가 기능
        b.chart1.description.isEnabled = true
        val description = Description()
        description.text = "캔들 차트"
        b.chart1.description = description

        //차트에 왼쪽 하단부에 데이터 범례 세팅 여부
        b.chart1.legend.isEnabled = true



        chartDataSetting()
    }

    private fun chartDataSetting() {

        var progress = 30

        b.chart1.resetTracking() // seekbar에 따라 차트 다시 그리기
        //데이터 생성 시작

        var mpPointD: MPPointD
//        mpPointD.


        var values = ArrayList<CandleEntry>()

        for (i in 0..progress) {
            var multi = b.seekBar2.progress + 1
            var value: Float = ((Math.random() * 40) + multi).toFloat()

            var high: Float = ((Math.random() * 9) + 8f).toFloat()
            var low: Float = ((Math.random() * 9) + 8f).toFloat()

            var open: Float = ((Math.random() * 6) + 1f).toFloat()
            var close: Float = ((Math.random() * 6) + 1f).toFloat()

            var even: Boolean = i % 2 == 0

            values.add(
                CandleEntry(
                    i.toFloat(), value + high,
                    value + low,
                    if (even) value + open else value - open,
                    if (even) value - close else value + close,
                    resources.getDrawable(R.drawable.star)
                )
            )

        }

        //데이터 생성 끝

        var set1 = CandleDataSet(values, "Data set")

        set1.setDrawIcons(false)
        set1.axisDependency = YAxis.AxisDependency.LEFT


        set1.shadowColor = Color.BLACK // 각 항목에 나타나는 세로 막대의 컬러
        set1.shadowWidth = 1f
        set1.shadowColorSameAsCandle = false // 세로 막대 컬러를 항목 컬러와 일치시킬지 여부

        //항목의 컬러 및 박스 유형 설정
        //open > close
        set1.decreasingColor = Color.RED
        set1.decreasingPaintStyle = Paint.Style.FILL

        //open <= close
        set1.increasingColor = Color.CYAN
        set1.increasingPaintStyle = Paint.Style.FILL

        // open == close
        set1.neutralColor = Color.BLUE

        var data = CandleData(set1)

        b.chart1.data = data
        b.chart1.invalidate()
    }


    @SuppressLint("ClickableViewAccessibility")
    fun setupListener() {
        b.chart1.setOnClickListener {
            Log.d(TAG, "setupListener: x : ${b.chart1.x}")
            Log.d(TAG, "setupListener: xAxis ${b.chart1.xAxis}")
            Log.d(TAG, "setupListener: ${b.chart1.pivotX}")

        }

        for (set in b.chart1.data.dataSets) {
            set.setDrawIcons(!set.isDrawIconsEnabled)
            set.yMin
        }




//        var mLastTouchX: Float = 0f
//        var mLastTouchY: Float = 0f
//        b.chart1.setOnTouchListener { view, motion ->
//            Log.d(TAG, "setupListener: motion : $motion")
//            when (MotionEventCompat.getActionMasked(motion)) {
//                MotionEvent.ACTION_DOWN -> {
//                    MotionEventCompat.getActionIndex(motion).also { pointerIndex ->
//                        // Remember where we started (for dragging)
//                        mLastTouchX = MotionEventCompat.getX(motion, pointerIndex)
//                        mLastTouchY = MotionEventCompat.getY(motion, pointerIndex)
//                        Log.d(
//                            TAG,
//                            "setupListener: mLastTouchX : $mLastTouchX, mLastTouchY : $mLastTouchY"
//                        )
//
//
//
//                    }
//                }
//
//            }
//            Log.d(TAG, "onTouch: 차트 클릭")
//            true
//        }

//        b.chart1.setOnClickListener {
//            Log.d(TAG, "setupListener: 클릭 ")
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.candle, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onProgressChanged(seekBar: SeekBar?, p0: Int, fromUser: Boolean) {
//
//        var progress = b.seekBar1.progress
////        tvX.text = progress.toString()
////        tvY.text = seekBarY.progress.toString()
//
//        b.chart1.resetTracking() // seekbar에 따라 차트 다시 그리기
//
//
//        //데이터 생성 시작
//        var values = ArrayList<CandleEntry>()
//
//        for (i in 0..progress) {
//            var multi = b.seekBar2.progress + 1
//            var value: Float = ((Math.random() * 40) + multi).toFloat()
//
//            var high: Float = ((Math.random() * 9) + 8f).toFloat()
//            var low: Float = ((Math.random() * 9) + 8f).toFloat()
//
//            var open: Float = ((Math.random() * 6) + 1f).toFloat()
//            var close: Float = ((Math.random() * 6) + 1f).toFloat()
//
//            var even: Boolean = i % 2 == 0
//
//            values.add(
//                CandleEntry(
//                    i.toFloat(), value + high,
//                    value + low,
//                    if (even) value + open else value - open,
//                    if (even) value - close else value + close,
//                    resources.getDrawable(R.drawable.star)
//                )
//            )
//
//        }
//        //데이터 생성 끝
//
//        var set1 = CandleDataSet(values, "Data set")
//
//        set1.setDrawIcons(false)
//        set1.axisDependency = YAxis.AxisDependency.LEFT
//
//
//        set1.shadowColor = Color.BLACK // 각 항목에 나타나는 세로 막대의 컬러
//        set1.shadowWidth = 1f
//        set1.shadowColorSameAsCandle = false // 세로 막대 컬러를 항목 컬러와 일치시킬지 여부
//
//        //항목의 컬러 및 박스 유형 설정
//        //open > close
//        set1.decreasingColor = Color.RED
//        set1.decreasingPaintStyle = Paint.Style.FILL
//
//        //open <= close
//        set1.increasingColor = Color.CYAN
//        set1.increasingPaintStyle = Paint.Style.FILL
//
//        // open == close
//        set1.neutralColor = Color.BLUE
//
//        var data = CandleData(set1)
//
//        b.chart1.data = data
//        b.chart1.invalidate()

    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }
}