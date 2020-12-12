package com.jinhanexample.mpchart.calendar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.github.mikephil.charting.data.Entry
import com.jinhanexample.Common
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityCalendarMPChartBinding
import java.util.*


class MPCalendarView(context: Context, attr: AttributeSet) : FrameLayout(context, attr) {

    lateinit var b: ActivityCalendarMPChartBinding
    private val SELECT_DURATION = 500
    private val SCORE_BAR_DURATION = 1000


    // 날짜별 감정 데이터
    private val hData: HashMap<Int, DayInfo>? = null

    // MP차트
    private val entries: List<Entry> = ArrayList()
    private var fStartXAxis = 0f

    var aa = 0

    // 사이즈
    var calendarWidth: Int = measuredWidth// CalenderView width

    var calendarHeight: Int = measuredHeight // CalenderView height

    private var unitX = 0f // 점선 간격

    private var sideMargin = 0f // 점선이 시작되기까지 margin

    private var lineChartHeight = 0 // 라인 차트 높이


    // 점선
    private val arrLines: ArrayList<ImageView>? = null


    // 날짜 버튼 사이즈
    private var dateBtnWidth = 0
    private var dateBtnHeight = 0
    private val arrDateBtns: ArrayList<Button>? = null
    private val nSelected = 0
    private var bAnimation = false

    // 마커

    // 점수표시
    private val fScoreBarWidth = 0f
    private val fScoreBarLeftMargin = 0f
    private val fScorePointWidth = 0f
    private val arrFeelingText: ArrayList<TextView>? = null
    private val arrScoreImage: ArrayList<ImageView>? = null
    private val arrScorePoint: ArrayList<ImageView>? = null
    private val arrScoreText: ArrayList<TextView>? = null

    // 오늘 날짜
    private val nToday = 0


    init {
        setBackgroundColor(Color.WHITE)
        this.addView(LayoutInflater.from(context).inflate(R.layout.layout_calendar, null))
        b = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.layout_calendar, null, false
        )

        b.lineChart.setViewPortOffsets(0f, 0f, 0f, 0f)
        b.lineChart.invalidate()


        unitX = Common.getDP(context, 45) //점선간격
        sideMargin = (calendarWidth - (unitX * 6)) / 2
        lineChartHeight = b.lineChart.measuredHeight
        dateBtnWidth = Common.getDP(context, 32).toInt()
        dateBtnHeight = Common.getDP(context, 64).toInt()
        fStartXAxis = sideMargin / unitX // 무슨 값이지?

    }

    fun selectDate(nAdd: Int) {
        if (!bAnimation) {
            bAnimation = true
            animateMarker(nAdd)
        }

    }

    private fun animateMarker(nAdd: Int) {
        b.outMarker.scaleX = 0f
        b.outMarker.visibility = VISIBLE

        var info = getAddedDayInfo(nAdd)
        var x = info.x
        var y = info.y

        b.outMarker.x = x - (b.outMarker.width / 2)
        b.outMarker.y = y - (b.outMarker.height / 2)

        //TODO:: 시작


    }

    private fun getAddedDayInfo(nAdd: Int): DayInfo {
        return getDayInfo(nAdd)!!
    }

    private fun getDayInfo(nDate: Int): DayInfo? {
        var info: DayInfo? = null

        if (!hData.isNullOrEmpty()) {

            if (hData.containsKey(nDate)) {
                info = hData[nDate]!!
            }
        } else {
            info = DayInfo(nDate)
        }

        return info
    }




}