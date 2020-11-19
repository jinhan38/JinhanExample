package com.jinhanexample.codingLecture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.jinhanexample.R
import com.jinhanexample.codingLecture.intent.IntentActivity
import com.jinhanexample.databinding.ActivityCodingLectureMainBinding

class CodingLectureMainActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var b: ActivityCodingLectureMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_coding_lecture_main)

        setupListener()

    }

    fun setupListener() {
        b.intentExample.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.intentExample -> {
                startActivity(Intent(this, IntentActivity::class.java))
            }

        }
    }
}