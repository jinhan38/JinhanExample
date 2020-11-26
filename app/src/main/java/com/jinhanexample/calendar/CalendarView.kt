package com.jinhanexample.calendar

import android.content.Context
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_custom_calendar.view.*
import java.util.*
import kotlin.collections.ArrayList

class CalendarView(context: Context?) : LinearLayout(context) {

    lateinit var header: LinearLayout
    lateinit var btnPrev: ImageView
    lateinit var btnNext: ImageView
    lateinit var txtDate: TextView
    lateinit var grid: GridView

    init {
        initControl(context)
    }

    private fun initControl(context: Context?) {
        header = findViewById(R.id.calendar_header)
        btnPrev = findViewById(R.id.calendar_prev_button)
        btnNext = findViewById(R.id.calendar_next_button)
        txtDate = findViewById(R.id.calendar_date_display)
        grid = findViewById(R.id.calendar_grid)
    }

    private fun updateCalendar() {
        var cells = ArrayList<Date>()

    }
}