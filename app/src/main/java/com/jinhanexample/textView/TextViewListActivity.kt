package com.jinhanexample.textView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jinhanexample.BaseActivity
import com.jinhanexample.R

class TextViewListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view_list)

        setupListener()
    }

    override fun setupListener() {

        (findViewById<Button>(R.id.editText_char_change)).setOnClickListener {
            startActivity(Intent(this, EditTextCharChange::class.java))
        }

        (findViewById<Button>(R.id.editText_underline_color_change)).setOnClickListener {
            startActivity(Intent(this, EditTextUnderlineColorChange::class.java))
        }
    }

}