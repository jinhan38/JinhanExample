package com.jinhanexample.string

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_string_format.*

class StringFormatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string_format)

        tvStringFormat.text =
            String.format(getString(R.string.string_format_test), "Jinhan", "Hello World!");

    }
}