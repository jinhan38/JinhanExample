package com.jinhanexample.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import java.time.Instant

class KotlinPlayGroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_play_ground)

        val size = listOf(0, 3, 5, 7, 9)
        println("size : ${size.length()}")
        println("calFold : ${size.calFold()}")
        println("calReduce : ${size.calReduce()}")


    }

    fun <T> List<T>.length() = this.size

    fun List<Int>.calFold(): Int = this.fold(1) { a, b -> a + b }

    fun List<Int>.calReduce(): Int = this.reduce { a, b -> a + b }

}


