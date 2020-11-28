package com.jinhanexample.calendar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashSet

class CustomCalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_calendar)

        val events: HashSet<Date> = HashSet()
        events.add(Date())

        var cv: CustomCalendarView = findViewById(R.id.calendar_view)
        cv.updateCalendar(events)

        cv.setEventHandler(object : CustomCalendarView.EventHandler {
            override fun onDayLongPress(date: Date?) {
                if (date != null) {
                    var df: DateFormat = SimpleDateFormat.getDateInstance()
                    Toast.makeText(
                        this@CustomCalendarActivity,
                        df.format(date),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.calendar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.calendar_menu_setting){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}