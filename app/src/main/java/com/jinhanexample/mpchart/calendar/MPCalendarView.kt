package com.jinhanexample.mpchart.calendar

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.github.mikephil.charting.data.Entry
import com.jinhanexample.Common
import com.jinhanexample.R
import com.jinhanexample.animation.animBuilder.ObjectAnimationBuilder
import com.jinhanexample.databinding.ActivityCalendarMPChartBinding
import com.jinhanexample.others.Utils
import kotlinx.android.synthetic.main.layout_calendar.view.*
import java.lang.Exception
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MPCalendarView(context: Context, attr: AttributeSet) : FrameLayout(context, attr) {

    companion object {
        private const val TAG = "MPCalendarView"
    }

    lateinit var b: ActivityCalendarMPChartBinding
    private val SELECT_DURATION = 500
    private val SCORE_BAR_DURATION = 1000


    // 날짜별 감정 데이터
    private val hData: HashMap<Int, DayInfo>? = null

    // MP차트
    private val entries = ArrayList<Entry>()
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
    private val btnDateSelect: Button? = null
    private val arrDateBtns: ArrayList<Button>? = null
    private var nSelected = 0
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

    /**
     * nAdd는 선택된 날짜
     */
    fun selectDate(nAdd: Int) {
        if (!bAnimation) {
            bAnimation = true
            animateMarker(nAdd)
            animateLine(nSelected, nAdd)
            animateArrow(nAdd)
            animateScore(nAdd)
            animateDateSelector(nSelected, nAdd)
        }

    }

    /**
     * 날짜 선택
     */
    private fun animateDateSelector(nFrom: Int, nTo: Int) {
        val info = getAddedDayInfo(nTo)
        var x: Float = info.x
        x -= dateBtnWidth / 2

        ObjectAnimationBuilder.Builder(btnDateSelect, SELECT_DURATION, "x", x)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {
                    postDelayed({
                        val info = getAddedDayInfo(nFrom)
                        val btn = arrDateBtns?.get(nFrom + 6)
                        btn?.text = getDateString(info.date, false)
                    }, 100);

                    postDelayed({
                        val info = getAddedDayInfo(nTo)
                        val btn = arrDateBtns?.get(nFrom + 6)
                        btn?.text = getDateString(info.date, false)
                    }, (SELECT_DURATION - 100).toLong())

                }

                override fun onAnimationEnd(p0: Animator?) {
                    nSelected = nTo
                    bAnimation = false
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationRepeat(p0: Animator?) {
                }

            })

    }


    @SuppressLint("SimpleDateFormat")
    private fun getDateString(date: Int, bSelected: Boolean): SpannableString {
        var color = 0
        var span: SpannableString
        var str = ""

        try {
            var sdfIn = SimpleDateFormat("yyyyMMdd")
            var sdfOut = SimpleDateFormat("E", Locale.ENGLISH)
            var calendar = Calendar.getInstance()
            calendar.time = sdfIn.parse(date.toString())
            str = sdfOut.format(calendar.time)

            color = when (str.toLowerCase()) {
                "sat" -> {
                    resources.getColor(R.color.colorSaturday, null)
                }
                "sun" -> {
                    resources.getColor(R.color.colorSunday, null)
                }
                else -> {
                    resources.getColor(R.color.colorGray500, null)
                }
            }


            str = str.substring(0, 1)
            str += "\n"
            str += String.format("%02d", date % 100)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        span = SpannableString(str)
        span.setSpan(ForegroundColorSpan(color), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        color = when {
            bSelected -> {
                resources.getColor(R.color.colorWhite, null)
            }
            getDayInfo(date)!!.hasScoreData() -> {
                resources.getColor(R.color.colorBlack, null)
            }
            else -> {
                resources.getColor(R.color.colorGray400, null)
            }
        }

        span.setSpan(
            ForegroundColorSpan(color),
            str.length - 2,
            str.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        span.setSpan(
            AbsoluteSizeSpan(
                Common.getSP(context, 14).toInt()
            ), str.length - 2, str.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return span

    }

    private fun animateScore(nAdd: Int) {
        var info: DayInfo = getAddedDayInfo(nAdd)
        var arrScore = ArrayList<ScoreInfo>()

        var score: ScoreInfo
        var nFeeling = 0

        for (i in 0 until arrScore.size) {
            score = arrScore[i]
            nFeeling = score.emotion
            val ivScoreBar = arrScoreImage?.get(i)
            val ivScorePoint = arrScorePoint?.get(i)
            val tvScore = arrScoreText?.get(i)

            //감정명 setting
            arrFeelingText?.get(i)?.text = changeFeelingIntToString(score.emotion)

            //score bar color setting
            val drawableBar: GradientDrawable = ivScoreBar?.background as GradientDrawable
            drawableBar.setColor(getFeelingBarColor(nFeeling))

            val drawablePoint: GradientDrawable = ivScorePoint?.background as GradientDrawable
            drawablePoint.setColor(getFeelingBarColor(nFeeling))

            //TODO:: ValueAnimator 커스텀
            ValueAnimator.ofInt(score.score).apply {

                duration = SCORE_BAR_DURATION.toLong()

                addUpdateListener {
                    val value = it.animatedValue as Float
                    tvScore?.text = value.toString()

                    var barWidth = value * fScoreBarWidth / 100f

                    if (barWidth < fScoreBarWidth) barWidth = fScoreBarWidth

                    val paramBar = ivScoreBar.layoutParams as LayoutParams
                    paramBar.width = barWidth.toInt()
                    ivScoreBar.layoutParams = paramBar

                    val pointLeft = fScoreBarLeftMargin + (barWidth - fScorePointWidth)

                    val paramPoint = ivScorePoint.layoutParams as LayoutParams
                    paramPoint.leftMargin = pointLeft.toInt()
                    ivScorePoint.layoutParams = paramPoint
                }

                start()
            }

        }

    }


    private fun getFeelingBarColor(color: Int): Int {
        var selectedColor = 0
        when (color) {
            Utils.PINK -> selectedColor = R.color.brand_pink
            Utils.YELLOW -> selectedColor = R.color.brand_yellow
            Utils.BLUE -> selectedColor = R.color.brand_blue
            Utils.GREEN -> selectedColor = R.color.brand_green
            Utils.ORANGE -> selectedColor = R.color.brand_orange
            Utils.RED -> selectedColor = R.color.brand_red
            Utils.PURPLE -> selectedColor = R.color.brand_purple
            Utils.GRAY -> selectedColor = R.color.brand_gray
        }
        return resources.getColor(selectedColor)
    }

    private fun changeFeelingIntToString(currentColor: Int): String {
        var comment = ""
        when (currentColor) {
            Utils.PINK -> comment = "기대돼요"
            Utils.YELLOW -> comment = "즐거워요"
            Utils.BLUE -> comment = "뿌듯해요"
            Utils.GREEN -> comment = "편안해요"
            Utils.ORANGE -> comment = "불안해요"
            Utils.RED -> comment = "짜증나요"
            Utils.PURPLE -> comment = "무기력해요"
            Utils.GRAY -> comment = "공허해요"
        }
        return comment
    }

    /**
     * progress 말풍선 위에 있는 화살표
     */
    private fun animateArrow(nAdd: Int) {
        var info: DayInfo = getAddedDayInfo(nAdd = nAdd)
        ObjectAnimationBuilder.Builder(b.ivArrow, SELECT_DURATION, "x", info.x)
            .setInterpolator(AccelerateInterpolator()).build()
    }

    /**
     * 점선
     */
    private fun animateLine(nFrom: Int, nTo: Int) {
        arrLines?.get(nFrom + 6)?.alpha = 0.3f
        arrLines?.get(nTo + 6)?.alpha = 1f
    }

    /**
     * 차트 그래프에 있는 마커
     */
    private fun animateMarker(nAdd: Int) {
        b.outMarker.scaleX = 0f
        b.outMarker.visibility = VISIBLE

        var info = getAddedDayInfo(nAdd)
        var x = info.x
        var y = info.y

        b.outMarker.x = x - (b.outMarker.width / 2)
        b.outMarker.y = y - (b.outMarker.height / 2)

        ObjectAnimationBuilder.Builder(b.outMarker, SELECT_DURATION, "scaleX", 1f)
            .setInterpolator(AccelerateInterpolator()).build()
        ObjectAnimationBuilder.Builder(b.outMarker, SELECT_DURATION, "scaleY", 1f)
            .setInterpolator(AccelerateInterpolator()).build()


    }

    private fun getAddedDayInfo(nAdd: Int): DayInfo {
        return getDayInfo(nAdd)!!
    }


    /**
     * 날짜 정보 반환
     */
    private fun getDayInfo(nDate: Int): DayInfo? {
        return when {
            hData.isNullOrEmpty() -> DayInfo(nDate)
            hData.containsKey(nDate) -> hData[nDate]
            else -> null
        }
    }

    fun reDraw() {
        drawDateButtons()
    }

    fun setLineChartData() {

        var xAxis: Int
        var nAvg: Float
        var info: DayInfo

        for (i in -8..2){
            xAxis = i +6

            info = getAddedDayInfo(i)
            nAvg = info.getTop3AverageScore()
            entries.add(Entry(xAxis.toFloat(), nAvg))

            //TODO::시작
            
//            info.x()
        }


    }

    private fun calX(index : Int) : Float{
        return index*unitX + sideMargin
    }

    private fun drawDateButtons() {

    }


}