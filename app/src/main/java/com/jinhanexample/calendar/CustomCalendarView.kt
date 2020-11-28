package com.jinhanexample.calendar


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemLongClickListener
import com.jinhanexample.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CustomCalendarView : LinearLayout {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initControl(context = context, attrs = attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initControl(context = context, attrs = attrs)
    }

    companion object {
        private const val TAG = "CalendarView"
        var DAYS_COUNT = 42
        var DATE_FORMAT = "MMM-yyyy"
    }

    lateinit var dateFormat: String

    //Calendar 클래스 초기화
    private var currentDate = Calendar.getInstance()

    //event handler
    lateinit var eventHandler: EventHandler

    lateinit var header: LinearLayout
    lateinit var btnPrev: ImageView
    lateinit var btnNext: ImageView
    lateinit var txtDate: TextView
    lateinit var grid: GridView


    /**
     * layout inflate 및
     * setting methods
     */
    private fun initControl(context: Context?, attrs: AttributeSet?) {

        //앞서 만든 layout을 inflate시켜주세요
        //생성자에서 context를 받아왔고, super(context)를 해줬기 때문에
        // this 혹은 context로 context를 불러올 수 있습니다.
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_calendar, this)

        loadDateFormat(attrs)
        uiViewInit()
        setHandlers()

        //updateCalendar 메소드는 두개가 있습니다.
        //한개는 null을 허용한 event : HashSet<Date>? 파라미터가 있는 메소드
        //다른 하나는 updateCalendar(null)을 호출하는 메소드
        updateCalendar()
    }

    /**
     * DateFormat attr setting
     */
    private fun loadDateFormat(attrs: AttributeSet?) {
        //   res/values에 생성한 attrs파일 load
        //date format을 추가로 설정하기 위해 attribute를 설정해주세요
        //추가로 설정하지 않으면 기본 값 DATE_FORMAT = "yyyy MMM"으로 설정됩니다.
        dateFormat = DATE_FORMAT

//        var ta = context.obtainStyledAttributes(attrs, R.styleable.CalendarView)
//
//        try {
//            dateFormat = ta.getString(R.styleable.CalendarView_dateFormat).toString()
//            if (dateFormat == null) {
//                dateFormat = DATE_FORMAT
//            }
//        } finally {
//            ta.recycle()
//        }
    }

    /**
     * view 초기화
     */
    private fun uiViewInit() {
        header = findViewById(R.id.calendar_header)
        btnNext = findViewById(R.id.calendar_next_button)
        btnPrev = findViewById(R.id.calendar_prev_button)
        txtDate = findViewById(R.id.calendar_date_display)
        grid = findViewById(R.id.calendar_grid)
    }

    /**
     * 다음달, 이전달, 날짜 longClick리스너 세팅
     */
    private fun setHandlers() {
        btnNext.setOnClickListener {
            //이번 달에서 1달 후의 날짜
            currentDate.add(Calendar.MONTH, 1)
            updateCalendar()
        }
        btnPrev.setOnClickListener {
            //이번 달에서 1달 전의 날짜
            currentDate.add(Calendar.MONTH, -1)
            updateCalendar()
        }

        //gridView에서 특정 Item을 LongClick하면 발생하는 리스너
        grid.onItemLongClickListener =
            OnItemLongClickListener { view, cell, position, id -> // handle long-press
                //클릭한 Item(cell)의 Date 값 반환
                eventHandler.onDayLongPress(view.getItemAtPosition(position) as Date)
                true
            }
    }


    fun updateCalendar() {
        updateCalendar(null)
    }

    /**
     * GridView에 입력할 날짜 데이터 setting
     */
    @SuppressLint("SimpleDateFormat")
    fun updateCalendar(events: HashSet<Date>?) {
        var cells = ArrayList<Date>()

        //앞서 생성한 currentDate를 clone() 메서드를 이용해 복사합니다.
        var calendar = currentDate.clone() as Calendar

        //이번달이 시작할 cell 지점 설정
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        var monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1

        //
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell)

        while (cells.size < DAYS_COUNT) {
            Log.d(TAG, "updateCalendar: calendar.time : ${calendar.time}")
            cells.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        //타이틀 입력
        val sdf = SimpleDateFormat(dateFormat)
        txtDate.text = sdf.format(currentDate.time)

        grid.adapter = events?.let {
            CalendarAdapter(
                context, R.layout.calendar_grid_item, cells, it
            )
        }

    }


    private class CalendarAdapter : ArrayAdapter<Date> {

        lateinit var eventDays: HashSet<Date>
        lateinit var inflater: LayoutInflater

        constructor(
            context: Context,
            layout: Int,
            days: ArrayList<Date>,
            eventDays: HashSet<Date>
        ) : super(context, layout, days) {
            //layout은 calendar_grid_item
            this.eventDays = eventDays
            this.inflater = LayoutInflater.from(context)
        }

        //getView는 GridView에서 한개의 cell을 가져옵니다.
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater =
                parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            var view: View? = null
            view = inflater.inflate(R.layout.calendar_grid_item, parent, false)

            var textView = view!!.findViewById<TextView>(R.id.calendar_grid_view_cell)


            //getItem은 ArrayAdapter의 함수
            //ArrayList<Date>를 생성자에서 입력해주었기 때문에 getItem은 date를 가져옴옴
            var date = getItem(position)!!
            var day = date.date
            var month = date.month
            var year = date.year

            var today = Date()
            if (month != today.month || year != today.year) {
                textView.apply {
                    setTextColor(resources.getColor(R.color.gray, null))

                }
            } else if (month == today.month &&
                year == today.year &&
                day == today.day
            ) {
                textView.apply {
                    setTypeface(null, Typeface.BOLD)
                    setTextColor(Color.BLACK)
                }
            }


            if (eventDays != null) {
                for (eventDay in eventDays) {

                    //오늘 날짜와 앞뒤 1일의 cell에 style을 주었습니다.
                    if ((eventDay.date == day || eventDay.date == day - 1 || eventDay.date == day + 1) &&
                        eventDay.month == month &&
                        eventDay.year == year
                    ) {
                        view.setBackgroundColor(Color.CYAN)
                        textView.apply {
                            setTypeface(null, Typeface.BOLD)
                        }
                    }

                }
            }

            textView.text = date.date.toString()

            return view
        }
    }


    /**
     * Assign event handler to be passed needed events
     */
    @JvmName("setEventHandler1")
    fun setEventHandler(eventHandler: EventHandler?) {
        this.eventHandler = eventHandler!!
    }

    /**
     * This interface defines what events to be reported to
     * the outside world
     */
    interface EventHandler {
        fun onDayLongPress(date: Date?)
    }
}