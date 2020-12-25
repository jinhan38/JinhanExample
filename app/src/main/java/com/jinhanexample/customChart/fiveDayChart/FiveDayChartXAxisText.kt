package com.jinhanexample.customChart.fiveDayChart

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.jinhanexample.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class FiveDayChartXAxisText : FrameLayout {


    companion object {
        private const val TAG = "FiveDayChartXAxisText"
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(
        context: Context,
        xAxisTextDataArray: ArrayList<XAxisTextData>,
        dayType: Int
    ) : super(context) {
        this.xAxisTextDataArray = xAxisTextDataArray
        this.dayType = dayType

    }


    private var dayType = 0
    var xAxisTextDataArray = ArrayList<XAxisTextData>()
    var textViewArrayList = ArrayList<TextView>()


    fun setData(dayType: Int) {
        //dayType 0이면 요일별, 1이면 일자별
        when (dayType) {
            0 -> {
                setStringDayText()
            }
            1 -> {
                setIntDayText()
            }
        }
    }


    //요일로 보여주기
    private fun setStringDayText() {

        //TODO::토요일은 파란색, 일요일은 빨간색, today는 첫글자와 배경 이미지
        for (i in 0 until xAxisTextDataArray.size) {

            val textView = TextView(context)
            textView.textSize = 10f

            val cal = Calendar.getInstance()
            val transFormat = SimpleDateFormat("yyyy-MM-dd")
            var to: Date? = null
            try {
                to = transFormat.parse(xAxisTextDataArray[i].date)
                cal.time = to
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            val dayNum = cal.get(Calendar.DAY_OF_WEEK)

            if (i == xAxisTextDataArray.size - 1) {
                textView.setTextColor(setColor(context, R.color.white))
                textView.text = getDayOfWeekString(dayNum).substring(0, 1)
                textView.width = getDP(context, 10).toInt()
                textView.x = xAxisTextDataArray[i].x - getDP(context, 5)
                textViewArrayList.add(textView)

                addView(setTodayImageView())

            } else {

                textView.setTextColor(setColor(context, getDayColor(dayNum)))
                textView.text = getDayOfWeekString(dayNum)
                textView.width = getDP(context, 10).toInt()
                textView.x = xAxisTextDataArray[i].x - getDP(context, 10)
                textViewArrayList.add(textView)
            }

            textView.gravity = Gravity.CENTER_VERTICAL
            addView(textView)


        }

    }


    //날짜로 보여주기
    private fun setIntDayText() {

        for (i in 0 until xAxisTextDataArray.size) {

            val textView = TextView(context)
            textView.textSize = 10f

            val cal = Calendar.getInstance()
            val transFormat = SimpleDateFormat("yyyy-MM-dd")
            var to: Date? = null
            try {
                to = transFormat.parse(xAxisTextDataArray[i].date)
                cal.time = to
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            val dayNum = cal.get(Calendar.DAY_OF_WEEK)




            if (i == xAxisTextDataArray.size - 1) {
                textView.setTextColor(setColor(context, R.color.white))
                addView(setTodayImageView())

            } else {
                textView.setTextColor(setColor(context, getDayColor(dayNum)))
            }

            textView.gravity = Gravity.CENTER_VERTICAL

            textView.text = xAxisTextDataArray[i].date.substring(
                xAxisTextDataArray[i].date.length - 2,
                xAxisTextDataArray[i].date.length
            )


            textView.width = getDP(context, 20).toInt()
            textView.x = xAxisTextDataArray[i].x - getDP(context, 5)
            textViewArrayList.add(textView)
            addView(textView)
        }
    }


    private fun setTodayImageView(): ImageView {
        //오늘 날짜에 원 추가
        val imageView = ImageView(context)
        val layoutParams = LinearLayout.LayoutParams(
            getDP(context, 20).toInt(),
            getDP(context, 20).toInt()
        )
        imageView.layoutParams = layoutParams
        imageView.setBackgroundResource(R.drawable.black_circle)
        if (dayType == 0) {

            imageView.x = xAxisTextDataArray[4].x - getDP(context, 12)
            imageView.y = getDP(context, 5)
        } else {

            imageView.x = xAxisTextDataArray[4].x - getDP(context, 10) +1f
            imageView.y = getDP(context, 5)
        }

        return imageView
    }

    private fun getDP(context: Context, value: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            context.resources.displayMetrics
        )
    }

    private fun setColor(context: Context, color: Int): Int {
        return context.resources.getColor(color, null)
    }


    /**
     * Date Type, yyyy-MM-dd 형식의 데이터를 요일로 반환
     *
     * @param dayNum
     * @return
     */
    private fun getDayOfWeekString(dayNum: Int): String {
        var dayOfWeek = ""
        when (dayNum) {
            1 -> dayOfWeek = "Sun"
            2 -> dayOfWeek = "Mon"
            3 -> dayOfWeek = "Tue"
            4 -> dayOfWeek = "Wen"
            5 -> dayOfWeek = "Thu"
            6 -> dayOfWeek = "Fri"
            7 -> dayOfWeek = "Sat"
        }
        return dayOfWeek
    }


    /**
     * Date Type, yyyy-MM-dd 형식의 데이터를 요일로 반환
     *
     * @param dayNum
     * @return
     */
    private fun getDayColor(dayNum: Int): Int {
        var color = 0
        color = when (dayNum) {
            1 -> R.color.brand_red
            7 -> R.color.brand_blue
            else -> R.color.black
        }
        return color
    }
}