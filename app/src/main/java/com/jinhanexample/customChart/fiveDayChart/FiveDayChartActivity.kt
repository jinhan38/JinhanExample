package com.jinhanexample.customChart.fiveDayChart

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.Common
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_five_day_chart.*

class FiveDayChartActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "FiveDayChartActivity"
    }

    private var viewLeft = 0f
    private var viewTop = 0f
    private var viewRight = 0f
    private var viewBottom = 0f
    lateinit var fiveDayChartImage: FiveDayChartImage
    private var xAxisTextDataArray = ArrayList<XAxisTextData>()
    lateinit var fiveDayChartXAxisText: FiveDayChartXAxisText

    private var dateType: Int = 0
    private var xArrayList = ArrayList<Float>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five_day_chart)

        dayOfWeekChart.setOnClickListener {
            dateType = 0
            setXAxisTextView()
            dateTypeText.text = "요일"
            dateChart.setTextColor(Common.setColor(this, R.color.colorGray500))
            dateChart.setBackgroundColor(Common.setColor(this, R.color.Gray100))
            dayOfWeekChart.setTextColor(Common.setColor(this, R.color.black))
            dayOfWeekChart.setBackgroundColor(Common.setColor(this, R.color.white))

        }
        dateChart.setOnClickListener {
            dateType = 1
            setXAxisTextView()
            dateTypeText.text = "일자"
            dayOfWeekChart.setTextColor(Common.setColor(this, R.color.colorGray500))
            dayOfWeekChart.setBackgroundColor(Common.setColor(this, R.color.Gray100))
            dateChart.setTextColor(Common.setColor(this, R.color.black))
            dateChart.setBackgroundColor(Common.setColor(this, R.color.white))

        }
    }

    private fun setXAxisTextView() {
        fiveDayChartXAxisTextView.removeAllViews()
        initXAxisText(xArrayList)
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        viewLeft = fiveDayChartImageView.left.toFloat()
        viewTop = fiveDayChartImageView.top.toFloat()
        viewRight = fiveDayChartImageView.right.toFloat()
        viewBottom = fiveDayChartImageView.bottom.toFloat()
        var scoreArray: ArrayList<Int> = ArrayList()

        //데이터가 없는날은 0으로 넣을 것
        scoreArray.add(100)
        scoreArray.add(30)
        scoreArray.add(0)
        scoreArray.add(45)
        scoreArray.add(30)

        fiveDayChartImage =
            FiveDayChartImage(
                this,
                fiveDayChartImageView.width.toFloat(),
                fiveDayChartImageView.height.toFloat(),
                R.color.white,
                R.color.soft_gray,
                scoreArray
            )

        fiveDayChartImageView.addView(fiveDayChartImage)

//        fiveDayChartImage.postInvalidate()


    }


    /**
     * X축 Label 입력
     */
    fun initXAxisText(xArrayList: ArrayList<Float>) {
        this.xArrayList = xArrayList
        //날짜는 오늘을 포함한 5일을 불러온다.
        //ex) 오늘이 25일이라면 21, 22, 23, 24, 25일
        //xArrayList[] 값을 불러와서 chart에 찍힌 circle의 위치와 요일 text의 위치 맞춤
        //입력한 x 값은 fiveDayChartView 안에서의 x값이다.
        // 때문에 전체를 포함하고 있는 parentView left의 값과, fiveDayChartView left의 값 사이의 거리룰 구해 더해줘야 한다.

        val marginValue = fiveDayChartImageView.left

        xAxisTextDataArray.clear()

        xAxisTextDataArray.add(
            XAxisTextData(
                "2020-12-23",
                xArrayList[0] + marginValue
            )
        )
        xAxisTextDataArray.add(
            XAxisTextData(
                "2020-12-24",
                xArrayList[1] + marginValue
            )
        )
        xAxisTextDataArray.add(
            XAxisTextData(
                "2020-12-25",
                xArrayList[2] + marginValue
            )
        )
        xAxisTextDataArray.add(
            XAxisTextData(
                "2020-12-26",
                xArrayList[3] + marginValue
            )
        )
        xAxisTextDataArray.add(
            XAxisTextData(
                "2020-12-27",
                xArrayList[4] + marginValue
            )
        )


        fiveDayChartXAxisText = FiveDayChartXAxisText(this, xAxisTextDataArray, dateType)
        fiveDayChartXAxisText.setData(dateType)
        fiveDayChartXAxisTextView.addView(fiveDayChartXAxisText)


    }
}