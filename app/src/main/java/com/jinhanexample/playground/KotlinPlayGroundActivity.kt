package com.jinhanexample.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import java.lang.Exception
import java.time.Instant

class KotlinPlayGroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_play_ground)

        var str: String? = null
        var strValueFirst = str ?: "It is null"
        println("strValueFirst : $strValueFirst")

        str = "It is not null"
        var strValueSecond = str ?: "It is null"
        println("strValueSecond : $strValueSecond")


        var num: Int? = null
        var intValueFirst = num ?: 0
        println("intValueFirst : $intValueFirst")

        num = 11
        var intValueSecond = num ?: 0
        println("intValueSecond : $intValueSecond")

    }

}